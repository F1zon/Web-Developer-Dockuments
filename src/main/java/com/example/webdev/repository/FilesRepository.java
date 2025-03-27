package com.example.webdev.repository;

import com.example.webdev.db.dao.FilesDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilesRepository extends JpaRepository<FilesDao, Long> {

    @Query(value = "SELECT MAX(id_file) + 1 FROM файлы", nativeQuery = true)
    int getNexValId();

    @Query(value = "select * from файлы where contract = ?1", nativeQuery = true)
    FilesDao[] getFilesByContractId(int id);

    @Query(value = "select name from файлы where contract = ?1", nativeQuery = true)
    List<String> getNameByContractId(int id);
}
