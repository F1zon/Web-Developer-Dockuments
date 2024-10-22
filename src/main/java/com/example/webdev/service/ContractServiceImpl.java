package com.example.webdev.service;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContractServiceImpl {

    @Autowired
    private ContractRepository repository;

    public List<ContractDao> findAll() {
        return repository.findAll();
    }

    public int getCreateContractId() {
        return repository.getNextContactId();
    }

    public List<SmallContractDto> readAll() {
        List<String> results = repository.findAllContractsJoin();
        List<String> tmp = new ArrayList<>();

        List<SmallContractDto> result = new ArrayList<>();
        for (String s : results) {
            tmp = Arrays.asList(s.split(","));
            result.add(new SmallContractDto(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3), tmp.get(4), tmp.get(5)));
        }

        return result;
    }

    public List<CustomerDto> readAllCustomers() {
        List<String> request = repository.findAllCustomers();
        List<CustomerDto> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (String s : request) {
            tmp = Arrays.asList(s.split(","));
            result.add(new CustomerDto(Integer.parseInt(tmp.get(0)), tmp.get(1)));
        }

        return result;
    }

    public List<PersonalDto> readAllPersonals() {
        List<String> request = repository.findAllPersonals();
        List<PersonalDto> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (String s : request) {
            tmp = Arrays.asList(s.split(","));
            result.add(new PersonalDto(Integer.parseInt(tmp.get(0)), tmp.get(1), Integer.parseInt(tmp.get(2))));
        }

        return result;
    }

    public List<StatusDto> readAllStatuses() {
        List<String> request = repository.findAllStatus();
        List<StatusDto> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (String s : request) {
            tmp = Arrays.asList(s.split(","));
            result.add(new StatusDto(Integer.parseInt(tmp.get(0)), tmp.get(1)));
        }

        return result;
    }

    public List<DepartmentDto> readAllDepartments() {
        List<String> request = repository.findAllDepartments();
        List<DepartmentDto> result = new ArrayList<>();
        List<String> tmp = new ArrayList<>();
        for (String s : request) {
            tmp = Arrays.asList(s.split(","));
            result.add(new DepartmentDto(Integer.parseInt(tmp.get(0)), tmp.get(1)));
        }

        return result;
    }

    public void create(ContractDao model) {
        repository.save(model);
    }

    public ContractDao findById(int id) {
        return repository.findById(id).get();
    }

    public void delete(ContractDao model) {
        repository.delete(model);
    }

    public ContractDao creteDao(ContractModel model) {
        return new ContractDao(model.getObjects(), model.getCustomer(), model.getExecutor(),
                model.getResponsible(), model.getResponsible2(), model.getStates(), repository.getNexValId());
    }

    @Transactional
    public void save(ContractModel model) {
        repository.save(creteDao(model));
    }
}
