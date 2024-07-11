package com.example.webdev.repository;

import com.example.webdev.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentsRepository extends JpaRepository<Departments, Integer> {
}
