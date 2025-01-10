package com.example.webdev.controllers;

import com.example.webdev.db.dto.CustomerDto;
import com.example.webdev.db.dto.DateDto;
import com.example.webdev.db.dto.PersonalDto;
import com.example.webdev.db.dto.StatusDto;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class EditController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    public EditController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    //    Отправка данных по ID
    @GetMapping("/edited/customer")
    public CustomerDto getCustomerById(@RequestParam int id) {
        logger.info(contractService.readCustomerById(id).getName());
        return contractService.readCustomerById(id);
    }

    @GetMapping("/edited/personal")
    public List<PersonalDto> getPersonalById(@RequestParam int id) {
        return contractService.readPersonalById(id);
    }

    @GetMapping("/edited/status")
    public StatusDto getStatusById(@RequestParam int id) {
        return contractService.readStatusById(id);
    }

    @GetMapping("/edited/dates")
    public DateDto getDatesById(@RequestParam int id) {
        return contractService.readDateById(id);
    }
}
