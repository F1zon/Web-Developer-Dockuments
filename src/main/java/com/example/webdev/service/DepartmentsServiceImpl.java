package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Departments;
import com.example.webdev.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements UniMethods<Departments> {

    @Autowired
    private DepartmentsRepository repository;

    @Override
    public void create(Departments model) {
        repository.save(model);
    }

    @Override
    public List<Departments> readAll() {
        return repository.findAll();
    }

    @Override
    public Departments read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(Departments model, int id) {
        if (repository.existsById(id)) {
            model.setId(id);
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
