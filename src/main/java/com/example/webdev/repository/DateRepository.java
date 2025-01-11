package com.example.webdev.repository;

import com.example.webdev.db.dao.DateDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DateRepository extends JpaRepository<DateDao, Long> {
    @Query(value = "SELECT MAX(id_dat) + 1 FROM даты", nativeQuery = true)
    int getNexValId();

    @Query(value = """
        select * from даты where contract = ?1
        """, nativeQuery = true)
    String findDateDaoById(int id);
}
