package com.example.webdev.controllers;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FileModel;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CreateController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public CreateController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    @PostMapping("/create/contract")
    public ResponseEntity<?> createContract(@RequestBody ContractModel model) {
        logger.info("Created contract.....");

        contractService.save(model);
        logger.info("Contract is CREATE!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/date")
    public ResponseEntity<DateDao> createDate(@RequestBody DateModel model) {
        logger.info("Created dates.....");

        dateService.save(model, contractService.getCreateContractId());
        logger.info("Date is CREATE!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/fileWay")
    public ResponseEntity<?> creteFile(@RequestBody FileModel filesModel) {

//        TODO : Доработать хранение файов
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
