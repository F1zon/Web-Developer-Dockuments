package com.example.webdev.service;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dto.DateDto;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    public DateDao createDateDao(DateModel model, int idContract) {
        return new DateDao(model.getDateStart(), model.getDescription(), idContract, Optional.of(dateRepository.getNexValId()).orElse(1));
    }

    @Transactional
    public void save(DateModel model, int idContract) {
        dateRepository.save(createDateDao(model, idContract));
    }

    public DateDto findById(int id) {
        String[] request = dateRepository.findDateDaoById(id).split(",");
        return new DateDto(Integer.parseInt(request[0]), request[1], Integer.parseInt(request[2]), request[3]);
    }
}
