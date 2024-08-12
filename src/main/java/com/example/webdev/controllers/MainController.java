package com.example.webdev.controllers;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dao.FilesDao;
import com.example.webdev.db.dto.CustomerDto;
import com.example.webdev.db.dto.PersonalDto;
import com.example.webdev.db.dto.SmallContractDto;
import com.example.webdev.db.dto.StatusDto;
import com.example.webdev.repository.DateRepository;
import com.example.webdev.repository.FilesRepository;
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

    /**
     * POST запрос для добавления нового договора
     * @param contractDao Новый договор
     * @return HTTP Статус
     */
    @PostMapping(value = "/created")
    public ResponseEntity<ContractDao> addContract(@RequestBody ContractDao contractDao, @RequestBody DateDao dateDao, @RequestBody FilesDao filesDao) {
        int idContract = contractService.getCreateContractId();
        contractService.create(contractDao);

        fileService.create(filesDao, idContract);
        dateService.create(dateDao, idContract);

        return new ResponseEntity<>(contractDao, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/docks/{id}")
    public ResponseEntity<ContractDao> deleteContract(@PathVariable("id") int id) {
        ContractDao contractDao1 = contractService.findById(id);
        contractService.delete(contractDao1);
        return new ResponseEntity<>(contractDao1, HttpStatus.OK);
    }
}
