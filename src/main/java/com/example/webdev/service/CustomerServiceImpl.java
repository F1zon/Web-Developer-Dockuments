package com.example.webdev.service;

import com.example.webdev.interfaces.UniMethods;
import com.example.webdev.model.Customer;
import com.example.webdev.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements UniMethods<Customer> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void create(Customer model) {
        repository.save(model);
    }

    @Override
    public List<Customer> readAll() {
        return repository.findAll();
    }

    @Override
    public Customer read(int id) {
        return repository.getReferenceById(id);
    }

    @Override
    public boolean update(Customer model, int id) {
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
