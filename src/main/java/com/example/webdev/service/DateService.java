package com.example.webdev.service;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.dto.DateDto;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    @Transactional
    public void createDateDao(DateModel date, int idContract) {
        DateDao dao = new DateDao();
        dao.setIdContract(idContract);
        dao.setDateStart(date.getDateStart());
        dao.setDateEnd(date.getDateEnd());
        dao.setDescription(date.getDescription());
        dao.setStage(date.getStage());

        dateRepository.save(dao);
    }
}
