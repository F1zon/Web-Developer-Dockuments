package com.example.webdev.repository;

import com.example.webdev.db.dao.CustomerDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerDao, Integer> {
}
