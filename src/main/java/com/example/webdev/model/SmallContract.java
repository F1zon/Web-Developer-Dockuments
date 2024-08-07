package com.example.webdev.model;

import lombok.Data;

@Data
public class SmallContract {
//    Long idContract;
    String objects;
    String customer;
    String executor;
    String responsible;
    String department;
    String states;

    public SmallContract(String objects, String customer, String executor, String responsible, String department, String states) {
//        this.idContract = Long.valueOf(idContract);
        this.objects = objects;
        this.customer = customer;
        this.executor = executor;
        this.responsible = responsible;
        this.department = department;
        this.states = states;
    }
}
