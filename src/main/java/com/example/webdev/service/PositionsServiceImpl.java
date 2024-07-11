package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Positions;
import com.example.webdev.repository.PositionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsServiceImpl implements UniMethods<Positions> {

    @Autowired
    private PositionsRepository positionsRepository;


    @Override
    public void create(Positions model) {
        positionsRepository.save(model);
    }

    @Override
    public List<Positions> readAll() {
        return positionsRepository.findAll();
    }

    @Override
    public Positions read(int id) {
        return positionsRepository.getReferenceById(id);
    }

    @Override
    public boolean update(Positions model, int id) {
        if (positionsRepository.existsById(id)) {
            model.setIdPositions(id);
            positionsRepository.save(model);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (positionsRepository.existsById(id)) {
            positionsRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
