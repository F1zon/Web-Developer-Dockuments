package com.example.webdev.controllers;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FileModel;
import com.example.webdev.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class MainController {

    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;

    @Autowired
    public MainController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    @GetMapping(value = "/docks")
    public ResponseEntity<List<SmallContractDto>> getContracts() {
        final List<SmallContractDto> contracts = contractService.readAll();
        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET запрос для удобства заполнения информации
     * @return CreateNewContractModel (Заказчики, персонал, статусы)
     */
    @GetMapping(value = "/info/customers")
    public ResponseEntity<List<CustomerDto>> getInfoCustomer() {
        final List<CustomerDto> models = contractService.readAllCustomers();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/personal")
    public ResponseEntity<List<PersonalDto>> getInfoPersonal() {
        final List<PersonalDto> models = contractService.readAllPersonals();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/statuses")
    public ResponseEntity<List<StatusDto>> getInfoStatus() {
        final List<StatusDto> models = contractService.readAllStatuses();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/info/dep")
    public ResponseEntity<List<DepartmentDto>> getInfoDep() {
        final List<DepartmentDto> models = contractService.readAllDepartments();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/created")
    public ResponseEntity<?> addContract(@ModelAttribute("contract") ContractModel contractModel,
                                                   @ModelAttribute("date") DateModel dateModel,
                                                   @ModelAttribute("file") FileModel fileModel) {
        int idContract = contractService.getCreateContractId() + 1;
        contractService.createContract(contractModel);
        fileService.createFileDao(fileModel, idContract);
        dateService.createDateDao(dateModel, idContract);



        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/docks/{id}")
    public ResponseEntity<ContractDao> deleteContract(@PathVariable("id") int id) {
        ContractDao contractDao1 = contractService.findById(id);
        contractService.delete(contractDao1);
        return new ResponseEntity<>(contractDao1, HttpStatus.OK);
    }
}
