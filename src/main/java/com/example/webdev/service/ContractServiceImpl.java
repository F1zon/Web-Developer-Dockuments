package com.example.webdev.service;

import com.example.webdev.controllers.MainController;
import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.repository.ContractRepository;
import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class ContractServiceImpl {
    private final Logger logger = LoggerFactory.getLogger(ContractServiceImpl.class);

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
            result.add(new SmallContractDto(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3), tmp.get(4), tmp.get(5), tmp.get(6)));
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

    public CustomerDto readCustomerById(int id) {
        String request = repository.getCustomerById(id);
        return new CustomerDto(id, request);
    }

//    public List<PersonalDto> readPersonalById(int id) {
//        String[] request1 = repository.getResponsibleById(id).split(",");
//        String[] request2 = repository.getResponsible2ById(id).split(",");
//        List<PersonalDto> result = new ArrayList<>();
//        result.add(new PersonalDto(Integer.parseInt(request1[0]),
//                request1[1], Integer.parseInt(request1[2])));
//
//        result.add(new PersonalDto(Integer.parseInt(request2[0]),
//                request2[1], Integer.parseInt(request2[2])));
//
//        return result;
//    }

    public PersonalDto readPersonalByIdOne(int id) {
        String[] request = repository.getResponsibleById(id).split(",");
        logger.info(Arrays.toString(request));
        return new PersonalDto(Integer.parseInt(request[0]), request[1], Integer.parseInt(request[2]));
    }

    public PersonalDto readPersonalByIdTwo(int id) {
        String[] request = repository.getResponsible2ById(id).split(",");
        logger.info(Arrays.toString(request));
        return new PersonalDto(Integer.parseInt(request[0]), request[1], Integer.parseInt(request[2]));
    }

    public StatusDto readStatusById(int id) {
        String[] request = repository.getStatusById(id).split(",");
        logger.info(Arrays.toString(request));
        return new StatusDto(Integer.parseInt(request[0]), request[1]);
    }

    public ContractDto findByIdContract(int id) {
        String[] request = repository.findByIdContract(id).split(",");
        logger.info(Arrays.toString(request));
        return new ContractDto(Integer.parseInt(request[0]),
                request[1],
                Integer.parseInt(request[2]),
                request[3],
                Integer.parseInt(request[4]),
                Integer.parseInt(request[5]),
                Integer.parseInt(request[6]));
    }

    public ContractDao findById(int id) {
        return repository.findById(id).get();
    }

    public void delete(int id) {
        repository.deleteContractAndDateById((long) id);
    }

    public ContractDao creteDao(ContractModel model) {
        return new ContractDao(model.getObjects(), model.getCustomer(), model.getExecutor(),
                model.getResponsible(), model.getResponsible2(), model.getStates(), repository.getNexValId());
    }

    @Transactional
    public void save(ContractModel model) {
        repository.save(creteDao(model));
    }

    @Transactional
    public void updateContract(ContractModel model) {
        repository.updateContract(model.getObjects(), model.getCustomer(), model.getExecutor(), model.getResponsible(),
                model.getResponsible2(), model.getStates(), model.getId());
    }
}
