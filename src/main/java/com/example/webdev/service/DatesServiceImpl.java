package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.DatesModel;
import com.example.webdev.repository.DatesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DatesServiceImpl implements UniMethods<DatesModel> {

    @Autowired
    private DatesRepository repository;

    @Override
    public void create(DatesModel model) {
        repository.save(model);
    }

    @Override
    public List<DatesModel> readAll() {
        return repository.findAll();
    }

    @Override
    public DatesModel read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(DatesModel model, int id) {
        if (repository.existsById(id)) {
            model.setId_dat(id);
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
