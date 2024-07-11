package com.example.webdev.repository;

import com.example.webdev.model.Personal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<Personal, Integer> {
}
