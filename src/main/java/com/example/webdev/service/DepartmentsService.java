package com.example.webdev.service;

import com.example.webdev.model.Departments;
import com.example.webdev.repository.DepartmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsService {
    private final DepartmentsRepository departmentsRepository;

    @Autowired
    public DepartmentsService(DepartmentsRepository departmentsRepository) {
        this.departmentsRepository = departmentsRepository;
    }

    public List<Departments> getAllDepartments() {
        return departmentsRepository.findAll();
    }
}
