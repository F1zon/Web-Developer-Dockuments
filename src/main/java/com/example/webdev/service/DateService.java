package com.example.webdev.service;

import com.example.webdev.model.DateModel;
import com.example.webdev.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DateService {

    @Autowired
    private DateRepository dateRepository;

    public void createData(DateModel dateModel) {
        dateRepository.createDate(dateModel);
    }
}
