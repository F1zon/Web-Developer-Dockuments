package com.example.webdev.db.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractModel {
    int idContract;
    String objects;
    int customer;
    String executor;
    int responsible;
    int responsible2;
    int states;

    public ContractModel(String objects, int customer, String executor, int responsible, int responsible2, int states) {
        this.objects = objects;
        this.customer = customer;
        this.executor = executor;
        this.responsible = responsible;
        this.responsible2 = responsible2;
        this.states = states;
    }
}
