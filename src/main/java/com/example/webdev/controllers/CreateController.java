package com.example.webdev.controllers;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dto.StageDto;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FileModel;
import com.example.webdev.db.model.FullContractModel;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import com.example.webdev.service.StageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class CreateController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final StageService stageService;
    private final Logger logger = LoggerFactory.getLogger(MainController.class);
    private final ObjectMapper objectMapper;

    @Autowired
    public CreateController(ContractServiceImpl contractService,
                            DateService dateService,
                            FileService fileService,
                            StageService stageService,
                            ObjectMapper objectMapper) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
        this.stageService = stageService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/create/contract")
    public ResponseEntity<?> createContract(@RequestPart("model") String modelJson,
                                            @RequestPart(value = "fileArr", required = false) MultipartFile[] files) {

        try {
            // Преобразуем JSON-строку в объект FullContractModel
            FullContractModel model = objectMapper.readValue(modelJson, FullContractModel.class);
            logger.info("Update contract: {}", model.getObject());

            ContractModel contractModel = new ContractModel(model.getObject(),
                    model.getCustomer(),
                    model.getExecutor(),
                    model.getResponsibleOne(),
                    model.getResponsibleTwo(),
                    model.getStatus()
            );

            DateModel dateModel = new DateModel(model.getDate(), model.getDescription(), model.getId());
            StageDto[] arrStageDto = model.getStageDtoArr();
            contractService.save(contractModel);

            int currentId = contractService.getCreateContractId();
            dateService.save(dateModel, currentId);

            StageDto[] requestStage = stageService.findByContractId(currentId);
            logger.info("request in bd stage {}", (Object) requestStage);

            if (requestStage == null || requestStage.length == 0) {
                for (StageDto stage : arrStageDto) {
                    stage.setContract(currentId);
                    logger.info("Creating stage {}", stage.toString());
                    stageService.save(stage);
                }
            } else {
                stageService.synchronizeStages(Arrays.stream(arrStageDto).toList(), currentId);
            }

            // Обрабатываем загруженные файлы (если они есть)
            if (files != null && files.length > 0) {
                fileService.save(files, currentId);
            }

            logger.info("Contract is CREATE!");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            logger.error("Error parsing JSON or processing file: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Ошибка при обработке данных");
        }
    }
}
