package com.example.webdev.repository;

import com.example.webdev.model.DateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<DateModel, Long> {
}
