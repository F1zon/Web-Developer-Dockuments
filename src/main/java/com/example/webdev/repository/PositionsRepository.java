package com.example.webdev.repository;

import com.example.webdev.model.Positions;
import com.example.webdev.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionsRepository extends JpaRepository<Positions, Integer> {
}

