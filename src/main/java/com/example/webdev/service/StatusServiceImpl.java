package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Status;
import com.example.webdev.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImpl implements UniMethods<Status> {

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public void create(Status model) {
        statusRepository.save(model);
    }

    @Override
    public List<Status> readAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status read(int id) {
        return statusRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Status model, int id) {
        if (statusRepository.existsById(id)) {
            model.setId(id);
            statusRepository.save(model);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (statusRepository.existsById(id)) {
            statusRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
