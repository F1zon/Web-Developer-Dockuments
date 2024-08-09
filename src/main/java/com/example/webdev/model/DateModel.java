package com.example.webdev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "даты")
public class DateModel {
    @Id
    @Column(name = "id_dat")
    private int id;

    @Column(name = "date_start")
    private Date dateStart;
    @Column(name = "date_end")
    private Date dateEnd;
    @Column(name = "description")
    private String description;
    @Column(name = "stages")
    private String stage;
    @Column(name = "contract")
    private int idContract;
}
