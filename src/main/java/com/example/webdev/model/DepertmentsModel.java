package com.example.webdev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "отделы")
public class DepertmentsModel {
    @Id
    @Column(name = "id_departments")
    private Long id;

    @Column(name = "title")
    private String title;
}
