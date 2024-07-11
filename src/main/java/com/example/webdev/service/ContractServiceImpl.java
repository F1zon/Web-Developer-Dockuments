package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Contract;
import com.example.webdev.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractServiceImpl implements UniMethods<Contract> {

    @Autowired
    private ContractRepository repository;

    @Override
    public void create(Contract model) {
        repository.save(model);
    }

    @Override
    public List<Contract> readAll() {
        return repository.findAll();
    }

    @Override
    public Contract read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(Contract model, int id) {
        if (repository.existsById(id)) {
            model.setIdContract(id);
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
