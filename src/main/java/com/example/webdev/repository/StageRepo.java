package com.example.webdev.repository;

import com.example.webdev.db.dao.StageDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StageRepo extends JpaRepository<StageDao, Integer> {

    // Поиск всех этапов с определёным id контракта
    @Query(value = "select * from этапы where contract = ?1", nativeQuery = true)
    StageDao[] findByContractId(int id);

    // Удаление этапов по id контракта
    @Query(value = "delete from этапы where contract = ?1", nativeQuery = true)
    @Modifying
    void deleteDtoById(int id);

    // Удаление этапа по его id
    @Query(value = "delete  from этапы where id = ?1", nativeQuery = true)
    @Modifying
    void deleteById(int id);
}
