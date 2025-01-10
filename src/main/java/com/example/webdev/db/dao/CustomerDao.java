package com.example.webdev.db.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "заказчики")
public class CustomerDao {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
    @Column(name = "ogrn")
    private String ogm;
    @Column(name = "inn")
    private String inn;
    @Column(name = "kpp")
    private String kpp;
    @Column(name = "r/s")
    private String rs;
    @Column(name = "k/s")
    private String ks;
    @Column(name = "bank")
    private String bank;
    @Column(name = "inn_b")
    private String inn_b;
    @Column(name = "kpp_b")
    private String kpp_b;
    @Column(name = "bik")
    private String bik;
    @Column(name = "cod_okpo")
    private String cod_okpo;
    @Column(name = "ogrn_b")
    private String ogrn_b;
    @Column(name = "responsible")
    private String responsible;
}
