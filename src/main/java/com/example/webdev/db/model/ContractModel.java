package com.example.webdev.db.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractModel {
    int id;
    String objects;
    int customer;
    String executor;
    int responsible;
    int responsible2;
    int states;
}
