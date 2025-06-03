package com.example.webdev.controllers;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class MainController {

    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final StageService stageService;
    private final Logger log = LoggerFactory.getLogger(MainController.class);
//    private final FileModel files = new FileModel();
    private List<MultipartFile> files = new ArrayList<>();

    @Autowired
    public MainController(ContractServiceImpl contractService, DateService dateService, FileService fileService, StageService stageService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
        this.stageService = stageService;
    }

    @GetMapping(value = "/docks")
    public ResponseEntity<List<SmallContractDto>> getContracts() {
        final List<SmallContractDto> contracts = contractService.readAll();

        for (SmallContractDto contract : contracts) {
            final StageDto[] stage = stageService.findByContractId(contract.getIdContract());
            contract.setDateStart(stage[0].getDateStartStage());
            contract.setDateEnd(stage[stage.length - 1].getDateEndStage());
        }

        return !contracts.isEmpty()
                ? new ResponseEntity<>(contracts, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<ContractDao> deleteContract(@RequestParam int id) {
        log.info("Deleting contract with id {}", id);
        fileService.deleteByContractId(id);
        contractService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
