package com.example.webdev.service;

import com.example.webdev.db.dao.FilesDao;
import com.example.webdev.db.dto.FilesDto;
import com.example.webdev.db.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webdev.repository.FilesRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FilesRepository fileRepository;

    /**
     * Преобразует массив MultipartFile в массив File с сохранением оригинальных имен файлов.
     *
     * @param multipartFiles массив MultipartFile
     * @param targetDirectory путь к целевой директории для сохранения файлов
     * @return массив File с сохраненными файлами
     * @throws IOException если возникла ошибка при работе с файлами
     */
    public File[] convertMultipartFilesToFiles(MultipartFile[] multipartFiles, String targetDirectory) throws IOException {
        if (multipartFiles == null || multipartFiles.length == 0) {
            return new File[0]; // Возвращаем пустой массив, если входной массив пуст
        }

        // Создаем целевую директорию, если она не существует
        Path directoryPath = Paths.get(targetDirectory);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        List<File> fileList = new ArrayList<>();

        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                // Получаем оригинальное имя файла
                String originalFilename = multipartFile.getOriginalFilename();
                if (originalFilename == null || originalFilename.isEmpty()) {
                    throw new IllegalArgumentException("Имя файла не может быть пустым");
                }

                // Создаем файл в целевой директории с оригинальным именем
                File targetFile = new File(directoryPath.toFile(), originalFilename);

                // Если файл уже существует, добавляем уникальный суффикс
                if (targetFile.exists()) {
                    String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
                    String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
                    targetFile = new File(directoryPath.toFile(), fileNameWithoutExtension + "_" + System.currentTimeMillis() + extension);
                }

                // Копируем содержимое MultipartFile в целевой файл
                try (InputStream inputStream = multipartFile.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(targetFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                fileList.add(targetFile); // Добавляем сохраненный файл в список
            }
        }

        // Преобразуем список в массив и возвращаем его
        return fileList.toArray(new File[fileList.size()]);
    }

    /**
     * Метод для сохранения файлов в директорию.
     *
     * @param files массив файлов для сохранения
     * @param id    идентификатор, используемый для создания имени директории
     * @throws IOException если возникла ошибка при создании директории или копировании файлов
     */
    public void saveFilesToDirectory(File[] files, int id) throws IOException {
        // Определяем базовый путь для хранения файлов
        String baseDirectoryPath = "../files/";
        // Создаем имя директории на основе ID
        String directoryName = "contr" + id;
        // Полный путь к новой директории
        File directory = new File(baseDirectoryPath, directoryName);

        // Создаем директорию, если она не существует
        if (!directory.exists()) {
            boolean isCreated = directory.mkdirs(); // Создает директорию и все родительские директории
            if (!isCreated) {
                throw new IOException("Не удалось создать директорию: " + directory.getAbsolutePath());
            }
        }

        // Копируем каждый файл из массива в созданную директорию
        for (File file : files) {
            if (file == null || !file.exists()) {
                System.out.println("Файл не существует или равен null: " + (file != null ? file.getName() : "null"));
                continue; // Пропускаем некорректные файлы
            }

            // Определяем целевой путь для файла
            File targetFile = new File(directory, file.getName());

            try {
                // Копируем файл в новую директорию
                Files.copy(file.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Файл успешно скопирован: " + targetFile.getAbsolutePath());
            } catch (IOException e) {
                // Логируем ошибку, но продолжаем обработку остальных файлов
                System.err.println("Ошибка при копировании файла " + file.getName() + ": " + e.getMessage());
            }
        }
    }

    /**
     * Удаляет массив файлов.
     */
    private void deleteFiles(File[] files) {
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (file != null && file.exists()) {
                boolean isDeleted = file.delete();
                if (!isDeleted) {
                    System.err.println("Не удалось удалить файл: " + file.getAbsolutePath());
                }
            }
        }
    }

//    public FilesDao CreateDao(MultipartFile file, int idContract) {
//        return new FilesDao(idContract, file., fileRepository.getNexValId());
//    }

    private FilesDto createFilesDto(FilesDao file) {
        return new FilesDto(file.getId(),
                file.getFileWay(),
                file.getIdContact(),
                file.getName());
    }

    public void save(MultipartFile[] files, int idContract) throws IOException {
        File[] arr = convertMultipartFilesToFiles(files, "../files/contr" + idContract);
        saveFilesToDirectory(arr, idContract);

        String tmpUrl = "../files/contr" + idContract + "/";
        for (File file : arr) {
            fileRepository.save(new FilesDao(tmpUrl + file.getName(), idContract, file.getName()));
        }

//        deleteFiles(arr);
    }

    public FilesDto[] getFilesByIdContract(int idContract) {
        FilesDao[] filesDaos = fileRepository.getFilesByContractId(idContract);
        FilesDto[] filesDtos = new FilesDto[filesDaos.length];
        for (int i = 0; i < filesDaos.length; i++) {
            filesDtos[i] = createFilesDto(filesDaos[i]);
        }

        return filesDtos;
    }

    public List<String> getFilesNamesByIdContract(int idContract) {
        return fileRepository.getNameByContractId(idContract);
    }
}
