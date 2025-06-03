package com.example.webdev.service;

import com.example.webdev.db.dao.StageDao;
import com.example.webdev.db.dto.StageDto;
import com.example.webdev.repository.StageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StageService {

    @Autowired
    private StageRepo repo;

//    CRUD
    private StageDao convertDtoToDaoWithoutId(StageDto dto) {
        return new StageDao(dto.getDateStartStage(),
                dto.getDescriptionStage(),
                dto.getDateEndStage(),
                dto.getContract());
    }

    private StageDao convertDtoToDao(StageDto dto) {
        return new StageDao(dto.getId(),
                dto.getDateStartStage(),
                dto.getDescriptionStage(),
                dto.getDateEndStage(),
                dto.getContract());
    }

    private StageDto convertDaoToDto(StageDao dao) {
        return new StageDto(dao.getId(), dao.getDateStartStage(), dao.getDescriptionStage(),
                dao.getDateEndStage(), dao.getContract());
    }

    public void save(StageDto stage) {
        repo.save(convertDtoToDaoWithoutId(stage));
    }

    public List<StageDao> findAll() {
        return repo.findAll();
    }

    public StageDao findById(int id) {
        return repo.findById(id).isPresent() ? repo.findById(id).get() : null;
    }

    public void update(StageDto stage) {
        repo.save(convertDtoToDao(stage));
    }

    public void delete(int id) {
        repo.deleteById(id);
    }

    // Поиск всех этапов с определёным id контракта
    public StageDto[] findByContractId(int id) {
        StageDao[] stageDaos = repo.findByContractId(id);
        StageDto[] dtos = new StageDto[stageDaos.length];
        for (int i = 0; i < stageDaos.length; i++) {
            dtos[i] = convertDaoToDto(stageDaos[i]);
        }

        return dtos;
    }

    public StageDto findStartStage(int id) {
        StageDao[] stageDaos = repo.findByContractId(id);
        return stageDaos.length > 0 ? convertDaoToDto(stageDaos[0]) : new StageDto();
    }

    /**
     * Синхронизирует массив StageDto с данными в базе данных.
     *
     * @param frontendStages Массив StageDto, полученный с фронтенда.
     * @param contractId     ID контракта, для которого выполняется синхронизация.
     */
    public void synchronizeStages(List<StageDto> frontendStages, int contractId) {
        List<StageDao> dbStages = Arrays.stream(repo.findByContractId(contractId)).toList();

        Map<Integer, StageDto> dbStageMap = dbStages.stream()
                .map(this::convertDaoToDto)
                .collect(Collectors.toMap(StageDto::getId, dto -> dto));

        Map<Integer, StageDto> frontendStageMap = frontendStages.stream()
                .collect(Collectors.toMap(StageDto::getId, dto -> dto));

        List<StageDto> newStages = frontendStages.stream()
                .filter(stage -> stage.getId() == 0 || !dbStageMap.containsKey(stage.getId()))
                .toList();

        List<StageDto> updatedStages = frontendStages.stream()
                .filter(stage -> stage.getId() != 0 && dbStageMap.containsKey(stage.getId()))
                .filter(stage -> !stage.equals(dbStageMap.get(stage.getId())))
                .toList();

        List<StageDto> deletedStages = dbStageMap.values().stream()
                .filter(stage -> !frontendStageMap.containsKey(stage.getId()))
                .toList();

        for (StageDto newStage : newStages) {
            StageDao stageEntity = convertDtoToDao(newStage);
            stageEntity.setContract(contractId); // Устанавливаем ID контракта
            repo.save(stageEntity);
        }

        for (StageDto updatedStage : updatedStages) {
            Optional<StageDao> optionalStage = repo.findById(updatedStage.getId());
            optionalStage.ifPresent(stage -> {
                stage.setDateStartStage(updatedStage.getDateStartStage());
                stage.setDescriptionStage(updatedStage.getDescriptionStage());
                stage.setDateEndStage(updatedStage.getDateEndStage());
                repo.save(stage);
            });
        }

        for (StageDto deletedStage : deletedStages) {
            repo.deleteById(deletedStage.getId());
        }
    }
}
