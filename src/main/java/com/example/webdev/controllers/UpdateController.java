package com.example.webdev.controllers;

import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.FullContractModel;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class UpdateController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    public UpdateController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    @PostMapping("/update/contract")
    public ResponseEntity<?> updateContract(@RequestBody FullContractModel model) {
        logger.info("Update contract {}", model.getObject());

        ContractModel contractModel = new ContractModel(model.getId(), model.getObject(),
                model.getCustomer(), model.getExecutor(),
                model.getResponsibleOne(), model.getResponsibleTwo(),
                model.getStatus());

        contractService.updateContract(contractModel);
        return ResponseEntity.ok().build();
    }
}
