package com.example.webdev.db.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Entity
@Table(name = "даты")
public class DateDao {
    @Id
    @Column(name = "id_dat")
    private int id;

    @Column(name = "date_start")
    private String dateStart;
    @Column(name = "description")
    private String description;
    @Column(name = "contract")
    private int idContract;

    public DateDao(String start, String description, int idContract, int id) {
        this.dateStart = start;
        this.description = description;
        this.idContract = idContract;
        this.id = id;
    }

    public DateDao() {

    }
}
