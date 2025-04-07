package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="finance_contract")
public class financeContractDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "date_formation")
    private String date_formation;
    @Column(name = "number")
    private String number;
    @Column(name = "item")
    private String item;
    @Column(name = "responsible_department")
    private String responsible_department;
    @Column(name = "amount")
    private String amount;
    @Column(name = "contract_id")
    private int contract_id;
}
