package com.example.webdev.service;

import com.example.webdev.model.Contract;
import com.example.webdev.model.SmallContract;
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

    public List<SmallContract> readAll() {
        List<String> results = repository.findAllContractsJoin();
        List<String> tmp = new ArrayList<>();

        List<SmallContract> result = new ArrayList<>();
        for (String s : results) {
            tmp = Arrays.asList(s.split(","));
            result.add(new SmallContract(tmp.get(0), tmp.get(1), tmp.get(2), tmp.get(3), tmp.get(4), tmp.get(5)));
        }

        return result;
    }

    public void create(Contract model) {
        repository.save(model);
    }

    public Contract findById(int id) {
        return repository.findById(id).get();
    }

    public void update(Contract model) {
        repository.setContractById(model.getObjects(), model.getCustomer(), model.getExecutor(),
                model.getFiles(), model.getResponsible(), model.getResponsible2(), model.getDates(),
                model.getStates(), model.getStates());
    }

    public void delete(Contract model) {
        repository.delete(model);
    }

}
