package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "договоры")
public class Contract {
    @Id
    @Column(name = "id_contract")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idContract;

    @Column(name = "objects")
    private String objects;

    @Column(name = "customer")
    private int customer;

    @Column(name = "executor")
    private String executor;

    @Column(name = "files")
    private int files;

    @Column(name = "responsible")
    private int responsible;

    @Column(name = "responsible_2")
    private int responsible2;

    @Column(name = "dates")
    private int dates;

    @Column(name = "states")
    private int states;
}
