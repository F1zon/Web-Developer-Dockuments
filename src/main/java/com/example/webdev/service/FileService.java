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

    public FilesDao CreateDao(String url, int idContract) {
        return new FilesDao(url, idContract, fileRepository.getNexValId());
    }

    public void save(String url, int idContract) {
        fileRepository.save(CreateDao(url, idContract));
    }

}
