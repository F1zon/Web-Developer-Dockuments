package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Personal;
import com.example.webdev.repository.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalServiceImpl implements UniMethods<Personal> {

    @Autowired
    private PersonalRepository repository;

    @Override
    public void create(Personal model) {
        repository.save(model);
    }

    @Override
    public List<Personal> readAll() {
        return repository.findAll();
    }

    @Override
    public Personal read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(Personal model, int id) {
        if (repository.existsById(id)) {
            model.setIdPersonal(id);
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
