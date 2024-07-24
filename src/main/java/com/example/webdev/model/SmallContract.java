package com.example.webdev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SmallContract {
    @Id
    Long id;

    @Column
    String object;
    @Column
    String customer;
    @Column
    String executor;
    @Column
    String responsible;
    @Column
    String states;
}
