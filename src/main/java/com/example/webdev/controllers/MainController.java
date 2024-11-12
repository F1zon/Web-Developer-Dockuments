package com.example.webdev.controllers;

import com.example.webdev.db.dao.ContractDao;
import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dto.*;
import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FileModel;
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
    private final Logger log = LoggerFactory.getLogger(MainController.class);
//    private final FileModel files = new FileModel();
    private List<MultipartFile> files = new ArrayList<>();


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

    @GetMapping(value = "/info/dep")
    public ResponseEntity<List<DepartmentDto>> getInfoDep() {
        final List<DepartmentDto> models = contractService.readAllDepartments();
        return models != null && !models.isEmpty()
                ? new ResponseEntity<>(models, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    Отправка данных по ID
    @GetMapping("/edited/customer")
    public CustomerDto getCustomerById(@RequestParam int id) {
        log.info(contractService.readCustomerById(id).getName());
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

    @PostMapping("/create/contract")
    public ResponseEntity<?> createContract(@RequestBody ContractModel model) {
        log.info("Created contract.....");

        contractService.save(model);
        log.info("Contract is CREATE!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/date")
    public ResponseEntity<DateDao> createDate(@RequestBody DateModel model) {
        log.info("Created dates.....");

        dateService.save(model, contractService.getCreateContractId());
        log.info("Date is CREATE!");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/create/fileWay")
    public ResponseEntity<?> creteFile(@RequestBody FileModel filesModel) {

//        TODO : Доработать хранение файов
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/docks/{id}")
    public ResponseEntity<ContractDao> deleteContract(@PathVariable("id") int id) {
        ContractDao contractDao1 = contractService.findById(id);
        contractService.delete(contractDao1);
        return new ResponseEntity<>(contractDao1, HttpStatus.OK);
    }
}
