package com.example.webdev.controllers;

import com.example.webdev.db.dto.*;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import com.example.webdev.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class EditController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final StageService stageService;

    @Autowired
    public EditController(ContractServiceImpl contractService, DateService dateService, FileService fileService, StageService stageService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
        this.stageService = stageService;
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
        StageDto[] stageDtos = stageService.findByContractId(id);
        List<String> files = fileService.getFilesNamesByIdContract(id);

        return new FullContractDto(componentContractDto.getId(), componentContractDto.getObjectTitle(), componentContractDto.getCustomerId(),
                componentContractDto.getExecutor(), componentContractDto.getResponsibleId(),
                personalDtoOne.getDepartmentId(), componentContractDto.getResponsible2Id(),
                personalDtoTwo.getDepartmentId(), componentContractDto.getStatus(),
                dateDto.getDateStart(), dateDto.getDescription(), stageDtos, files);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(
            @RequestParam int id,
            @RequestParam String fileName) throws MalformedURLException {
        String uploadDir = "../files/contr" + id;
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();
        Resource resource = new UrlResource(filePath.toUri());

        // Проверяем, существует ли файл
        if (!resource.exists()) {
            throw new RuntimeException("Файл не найден: " + fileName);
        }

        // Возвращаем файл как ResponseEntity
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
