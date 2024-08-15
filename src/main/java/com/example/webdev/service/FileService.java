package com.example.webdev.service;

import com.example.webdev.db.dao.FilesDao;
import com.example.webdev.db.dto.FilesDto;
import com.example.webdev.db.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webdev.repository.FilesRepository;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

    @Autowired
    private FilesRepository fileRepository;

    public void createFileDao(FileModel files, int idContract) {
        FilesDao filesDao = new FilesDao();
        filesDao.setIdContact(idContract);
        filesDao.setFileName(files.getFileName());

        fileRepository.save(filesDao);
    }

}
