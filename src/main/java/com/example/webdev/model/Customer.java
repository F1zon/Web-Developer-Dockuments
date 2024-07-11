package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "заказчики")
public class Customer {
    @Id
    @Column(name = "id_customer")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "ogrn")
    private String ogrn;

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
    private int responsible;
}
