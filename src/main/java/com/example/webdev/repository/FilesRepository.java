package com.example.webdev.repository;

import com.example.webdev.db.dao.FilesDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesDao, Long> {

    @Query(value = "SELECT MAX(id_file) + 1 FROM файлы", nativeQuery = true)
    int getNexValId();
}
