package com.example.webdev.repository;

import com.example.webdev.model.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<Files, Integer> {
}
