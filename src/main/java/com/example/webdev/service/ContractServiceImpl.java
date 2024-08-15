package com.example.webdev.service;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ContractServiceImpl {

    @Autowired
    private ContractRepository repository;

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
            result.add(new PersonalDto(Integer.parseInt(tmp.get(0)), tmp.get(1)));
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

    public void create(ContractDao model) {
        repository.save(model);
    }

    public ContractDao findById(int id) {
        return repository.findById(id).get();
    }

    public void delete(ContractDao model) {
        repository.delete(model);
    }

    public void createContract(ContractModel model) {
        ContractDao contractDao = new ContractDao();
        contractDao.setObjects(model.getObjects());
        contractDao.setExecutor(model.getExecutor());

        contractDao.setCustomer(repository.findCustomerByTitle(model.getCustomer()));
        contractDao.setResponsible(repository.findPersonalByFio(model.getResponsible()));
        if (model.getResponsible().equals(model.getResponsible2())) {
            contractDao.setResponsible2(contractDao.getResponsible());
        } else {
            contractDao.setResponsible2(repository.findPersonalByFio(model.getResponsible2()));
        }
        contractDao.setStates(repository.findStageByTitle(model.getStates()));

        repository.save(contractDao);
    }
}
