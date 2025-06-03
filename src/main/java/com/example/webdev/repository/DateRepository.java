package com.example.webdev.repository;

import com.example.webdev.db.dao.DateDao;
import com.example.webdev.db.model.DateModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DateRepository extends JpaRepository<DateDao, Long> {
    @Query(value = "SELECT MAX(id_dat) + 1 FROM даты", nativeQuery = true)
    int getNexValId();

    @Query(value = """
        select * from даты where contract = ?1
        """, nativeQuery = true)
    DateDao findDateDaoById(Long contract);

    @Transactional
    @Modifying
    @Query(value = "update даты set date_start = ?1, description = ?2 where id_dat = ?3", nativeQuery = true)
    void update(String dateStart, String description, int id);

    @Query(value = "SELECT * FROM даты WHERE contract = ?1", nativeQuery = true)
    DateDao findDateDaoByIdContract(Long idContract);
}
