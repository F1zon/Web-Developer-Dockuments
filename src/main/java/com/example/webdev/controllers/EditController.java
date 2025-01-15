package com.example.webdev.controllers;

import com.example.webdev.db.dto.*;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class EditController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;

    @Autowired
    public EditController(ContractServiceImpl contractService, DateService dateService, FileService fileService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
    }

    //    Отправка данных по ID
    @GetMapping("/edited/customer")
    public CustomerDto getCustomerById(@RequestParam int id) {
        return contractService.readCustomerById(id);
    }

    @GetMapping("/edited/personal")
    public List<PersonalDto> getPersonalById(@RequestParam int id) {
        List<PersonalDto> personalDtos = new ArrayList<>();
        personalDtos.add(contractService.readPersonalByIdOne(id));
        personalDtos.add(contractService.readPersonalByIdTwo(id));
        return personalDtos;
    }

    @GetMapping("/edited/status")
    public StatusDto getStatusById(@RequestParam int id) {
        return contractService.readStatusById(id);
    }

    @GetMapping("/edited/dates")
    public DateDto getDatesById(@RequestParam int id) {
        return dateService.findById(id);
    }
    @GetMapping("/edited/contract")
    public FullContractDto getContractById(@RequestParam int id) {
        ComponentContractDto componentContractDto = contractService.findByIdContract(id);
        PersonalDto personalDtoOne = contractService.readPersonalByIdOne(id);
        PersonalDto personalDtoTwo = contractService.readPersonalByIdTwo(id);
        DateDto dateDto = dateService.findById(id);

        return new FullContractDto(componentContractDto.getId(), componentContractDto.getObjectTitle(), componentContractDto.getCustomerId(),
                componentContractDto.getExecutor(), componentContractDto.getResponsibleId(),
                personalDtoOne.getDepartmentId(), componentContractDto.getResponsible2Id(),
                personalDtoTwo.getDepartmentId(), componentContractDto.getStatus(),
                dateDto.getDateStart(), dateDto.getDescription());
    }
}
