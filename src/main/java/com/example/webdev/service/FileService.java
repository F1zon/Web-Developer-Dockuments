package com.example.webdev.service;

import com.example.webdev.db.dao.FilesDao;
import com.example.webdev.db.model.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webdev.repository.FilesRepository;

@Service
public class FileService {

    @Autowired
    private FilesRepository fileRepository;

    public FilesDao CreateDao(FileModel model, int idContract) {
        return new FilesDao(model.getFileName(), idContract, fileRepository.getNexValId());
    }

    public void save(FileModel file, int idContract) {
        fileRepository.save(CreateDao(file, idContract));
    }

}
