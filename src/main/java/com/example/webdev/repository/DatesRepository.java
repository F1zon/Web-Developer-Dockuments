package com.example.webdev.repository;

import com.example.webdev.model.DatesModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatesRepository extends JpaRepository<DatesModel, Integer> {
}
