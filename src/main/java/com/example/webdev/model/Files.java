package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "файлы")
public class Files {
    @Id
    @Column(name = "id_file")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFile;

    @Column(name = "dock")
    private String dock;

    @Column(name = "pdf")
    private String pdf;

    @Column(name = "excel")
    private String excel;
}
