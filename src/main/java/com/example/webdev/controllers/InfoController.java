package com.example.webdev.controllers;

import com.example.webdev.db.dto.*;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class InfoController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public InfoController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    /**
     * GET запрос для удобства заполнения информации
     * @return CreateNewContractModel (Заказчики, персонал, статусы)
     */
    @GetMapping(value = "/info/customers")
    public ResponseEntity<List<CustomerDto>> getInfoCustomer() {
        logger.info("getInfoCustomer");
        final List<CustomerDto> models = contractService.readAllCustomers();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/personal")
    public ResponseEntity<List<PersonalDto>> getInfoPersonal() {
        logger.info("getInfoPersonal");
        final List<PersonalDto> models = contractService.readAllPersonals();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/statuses")
    public ResponseEntity<List<StatusDto>> getInfoStatus() {
        logger.info("getInfoStatus");
        final List<StatusDto> models = contractService.readAllStatuses();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/dep")
    public ResponseEntity<List<DepartmentDto>> getInfoDep() {
        logger.info("getInfoDep");
        final List<DepartmentDto> models = contractService.readAllDepartments();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
