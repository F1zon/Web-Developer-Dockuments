package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "даты")
public class DatesModel {
    @Id
    @Column(name = "id_dat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_dat;

    @Column(name = "date_start")
    private Date date_start;

    @Column(name = "date_end")
    private Date date_end;

    @Column(name = "description")
    private String description;

    @Column(name = "stages_1")
    private String stage1;
    @Column(name = "stages_2")
    private String stage2;
    @Column(name = "stages_3")
    private String stage3;
    @Column(name = "stages_4")
    private String stage4;
    @Column(name = "stages_5")
    private String stage5;
    @Column(name = "stages_6")
    private String stage6;
}
