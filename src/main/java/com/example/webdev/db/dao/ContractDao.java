package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "договоры")
public class ContractDao {
    @Id
    @Column(name = "id_contract")
    @GeneratedValue
    private int idContract;

    @Column(name = "objects")
    private String objects;

    @Column(name = "customer")
    private int customer;

    @Column(name = "executor")
    private String executor;

    @Column(name = "responsible")
    private int responsible;

    @Column(name = "responsible_2")
    private int responsible2;

    @Column(name = "states")
    private int states;
}
