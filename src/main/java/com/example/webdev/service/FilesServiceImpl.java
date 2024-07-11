package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Files;
import com.example.webdev.repository.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilesServiceImpl implements UniMethods<Files> {

    @Autowired
    private FilesRepository repository;

    @Override
    public void create(Files model) {
        repository.save(model);
    }

    @Override
    public List<Files> readAll() {
        return repository.findAll();
    }

    @Override
    public Files read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(Files model, int id) {
        if (repository.existsById(id)) {
            model.setIdFile(id);
            repository.save(model);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }

        return false;
    }
}
