package com.example.webdev.controllers;

import com.example.webdev.model.Contract;
import com.example.webdev.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {
    private final StatusServiceImpl statusService;
    private final PositionsServiceImpl positionsService;
    private final PersonalServiceImpl personalService;
    private final FilesServiceImpl filesService;
    private final DepartmentsServiceImpl departmentsService;
    private final DatesServiceImpl datesService;
    private final CustomerServiceImpl customerService;
    private final ContractServiceImpl contractService;

    @Autowired
    public MainController(StatusServiceImpl statusService, PositionsServiceImpl positionsService,
                          PersonalServiceImpl personalService, FilesServiceImpl filesService,
                          DepartmentsServiceImpl departmentsService, DatesServiceImpl datesService,
                          CustomerServiceImpl customerService, ContractServiceImpl contractService) {
        this.statusService = statusService;
        this.positionsService = positionsService;
        this.personalService = personalService;
        this.filesService = filesService;
        this.departmentsService = departmentsService;
        this.datesService = datesService;
        this.customerService = customerService;
        this.contractService = contractService;
    }

    @GetMapping(value = "/main")
    public ResponseEntity<List<Contract>> getContracts() {
        final List<Contract> contracts = contractService.readAll();
        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
