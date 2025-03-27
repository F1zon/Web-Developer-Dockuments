package com.example.webdev.controllers;

import com.example.webdev.db.dto.StageDto;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FullContractModel;
import com.example.webdev.service.ContractServiceImpl;
import com.example.webdev.service.DateService;
import com.example.webdev.service.FileService;
import com.example.webdev.service.StageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:3000", "http://localhost:4200" })
@RestController
public class UpdateController {
    private final ContractServiceImpl contractService;
    private final DateService dateService;
    private final FileService fileService;
    private final Logger logger = LoggerFactory.getLogger(UpdateController.class);
    private final ObjectMapper objectMapper; // Для преобразования JSON в объект
    private final StageService stageService;

    @Autowired
    public UpdateController(ContractServiceImpl contractService,
                            DateService dateService,
                            FileService fileService,
                            ObjectMapper objectMapper,
                            StageService stageService) {
        this.contractService = contractService;
        this.dateService = dateService;
        this.fileService = fileService;
        this.objectMapper = objectMapper;
        this.stageService = stageService;
    }

    /**
     * Обновление контракта.
     *
     * @param modelJson JSON-строка с данными контракта.
     * @param files Массив загруженных файлов.
     * @return ResponseEntity с результатом операции.
     */
    @PostMapping(value = "/update/contract", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateContract(@RequestPart("model") String modelJson,
                                            @RequestPart(value = "fileArr", required = false) MultipartFile[] files) {
        try {
            // Преобразуем JSON-строку в объект FullContractModel
            FullContractModel model = objectMapper.readValue(modelJson, FullContractModel.class);
            logger.info("Update contract: {}", model.getObject());

            // Создаем объект ContractModel из данных FullContractModel
            ContractModel contractModel = new ContractModel(
                    model.getId(),
                    model.getObject(),
                    model.getCustomer(),
                    model.getExecutor(),
                    model.getResponsibleOne(),
                    model.getResponsibleTwo(),
                    model.getStatus()
            );

            DateModel dateModel = new DateModel(
                    model.getDate(),
                    model.getDescription()
            );

            StageDto[] arrStageDto = model.getStageDtoArr();

            StageDto[] requestStage = stageService.findByContractId(model.getId());
            logger.info("request in bd stage {}", (Object) requestStage);

            if (requestStage == null || requestStage.length == 0) {
                for (StageDto stage : arrStageDto) {
                    stage.setContract(model.getId());
                    logger.info("Creating stage {}", stage.toString());
                    stageService.save(stage);
                }
            } else {
                stageService.synchronizeStages(Arrays.stream(arrStageDto).toList(), model.getId());
            }

            // Обновляем контракт в базе данных
            contractService.updateContract(contractModel);
            dateService.save(dateModel, model.getId());

            // Обрабатываем загруженные файлы (если они есть)
            if (files != null && files.length > 0) {
                fileService.save(files, model.getId());
            }

            return ResponseEntity.ok().build();
        } catch (IOException e) {
            logger.error("Error parsing JSON or processing files: {}", e.getMessage());
            return ResponseEntity.badRequest().body("Ошибка при обработке данных");
        }
    }
}
