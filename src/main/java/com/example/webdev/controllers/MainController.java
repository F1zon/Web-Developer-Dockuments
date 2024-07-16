package com.example.webdev.controllers;

import com.example.webdev.model.Contract;
import com.example.webdev.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    private final ContractServiceImpl contractService;

    @Autowired
    public MainController(ContractServiceImpl contractService) {
        this.contractService = contractService;
    }

    @GetMapping(value = "/docks")
    public ResponseEntity<List<List<String>>> getContracts() {
        final List<List<String>> contracts = contractService.readAll();
        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/created")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        contractService.create(contract);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }
}
