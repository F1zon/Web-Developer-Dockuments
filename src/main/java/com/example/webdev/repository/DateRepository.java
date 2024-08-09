package com.example.webdev.repository;

import com.example.webdev.model.DateModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface DateRepository extends JpaRepository<DateModel, Integer> {

    @Query(value = "insert into даты (date_start, date_end, description, stages, contract) VALUES " +
            "(:#{model.dateStart}, :#{model.dateEnd}, :#{model.description}, :#{model.stage}, :#{model.idContract})", nativeQuery = true)
    void createDate(DateModel model);
}
