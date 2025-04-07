package com.example.webdev.repository;

import com.example.webdev.db.dao.financeStagesDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface financeStagesRepo extends JpaRepository<financeStagesDao,Integer> {
}
