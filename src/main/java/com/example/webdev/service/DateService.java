package com.example.webdev.service;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dto.DateDto;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.repository.DateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    private final Logger logger = LoggerFactory.getLogger(DateService.class);

    private DateDto convertDaoToDto(DateDao dao) {
        return new DateDto(
            dao.getId(), dao.getDescription(), dao.getIdContract(), dao.getDateStart()
        );
    }

    public DateDto getDateDaoById(int id) {
        return convertDaoToDto(Objects.requireNonNull(dateRepository.findById((long) id).orElse(null)));
    }

    public DateDao createDateDao(DateModel model, int idContract) {
        return new DateDao(model.getDateStart(), model.getDescription(), idContract, Optional.of(dateRepository.getNexValId()).orElse(1));
    }

    @Transactional
    public void save(DateModel model, int idContract) {
        if (isDateExist(idContract)) {
            dateRepository.save(createDateDao(model, idContract));
        } else {
            update(model);
        }
    }

    public DateDto findById(int id) {
//        String[] request = dateRepository.findDateDaoById(id).split(",");

        return convertDaoToDto(dateRepository.findDateDaoById(id));
    }

    public void update(DateModel model) {
        dateRepository.update(model.getDateStart(), model.getDescription(), model.getIdContract());
    }

    public boolean isDateExist(int idContract) {
        return dateRepository.findById((long) idContract).isPresent();
    }
}
