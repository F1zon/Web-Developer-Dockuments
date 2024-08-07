package com.example.webdev.controllers;

import com.example.webdev.model.Contract;
import com.example.webdev.model.SmallContract;
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

    @Autowired
    public MainController(ContractServiceImpl contractService) {
        this.contractService = contractService;
    }

    @GetMapping(value = "/docks")
    public ResponseEntity<List<SmallContract>> getContracts() {
        final List<SmallContract> contracts = contractService.readAll();
        return contracts != null && !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * GET запрос для удобства заполнения информации
     * @return CreateNewContractModel (объекты, персонал, статусы)
     */
//    @GetMapping(value = "/info")
//    public ResponseEntity<CreateNewContractModel> getInfoDb() {
//        final CreateNewContractModel model = contractService.generateDbInfo();
//        return model != null
//                ? new ResponseEntity<>(model, HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

    /**
     * POST запрос для добавления нового договора
     * @param contract Новый договор
     * @return HTTP Статус
     */
    @PostMapping(value = "/created")
    public ResponseEntity<Contract> addContract(@RequestBody Contract contract) {
        contractService.create(contract);
        return new ResponseEntity<>(contract, HttpStatus.CREATED);
    }

    /**
     * PUT ответ, обновление записи в таблице
     * @param id int id Договора
     * @param contract Contract новая информация по договору
     * @return HTTP Статус
     */
    @PutMapping(value = "/docks/{id}")
    public ResponseEntity<Contract> updateContract(@PathVariable("id") int id, @RequestBody Contract contract) {
        Contract contract1 = contractService.findById(id);
        contractService.update(contract1);
        return new ResponseEntity<>(contract1, HttpStatus.OK);
    }

    @DeleteMapping(value = "/docks/{id}")
    public ResponseEntity<Contract> deleteContract(@PathVariable("id") int id) {
        Contract contract1 = contractService.findById(id);
        contractService.delete(contract1);
        return new ResponseEntity<>(contract1, HttpStatus.OK);
    }
}
