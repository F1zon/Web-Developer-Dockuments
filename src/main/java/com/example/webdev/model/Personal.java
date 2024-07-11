package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "сотрудники")
public class Personal {
    @Id
    @Column(name = "id_personal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersonal;

    @Column(name = "fio")
    private String fio;

    @Column(name = "post")
    private int post;

    @Column(name = "department")
    private int department;

    @Column(name = "mail")
    private String mail;

    @Column(name = "phone")
    private String phone;
}
