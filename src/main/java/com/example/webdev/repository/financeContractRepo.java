package com.example.webdev.repository;

import com.example.webdev.db.dao.financeContractDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface financeContractRepo extends JpaRepository<financeContractDao, Integer> {
}
