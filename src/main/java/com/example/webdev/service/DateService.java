package com.example.webdev.service;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    @Transactional
    public void save(DateDao dao) {
        dateRepository.save(dao);
    }
}
