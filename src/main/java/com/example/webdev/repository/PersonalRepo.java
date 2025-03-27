package com.example.webdev.repository;

import com.example.webdev.db.dao.PersonalDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface PersonalRepo extends JpaRepository<PersonalDao, Long> {
    Optional<PersonalDao> findByName(String username);
}
