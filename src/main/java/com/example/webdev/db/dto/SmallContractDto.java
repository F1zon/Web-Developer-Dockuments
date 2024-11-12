package com.example.webdev.db.dto;

import lombok.Data;

@Data
public class SmallContractDto {
    Long idContract;
    String objects;
    String customer;
    String executor;
    String responsible;
    String department;
    String states;

    public SmallContractDto(String idContract, String objects, String customer, String executor, String responsible, String department, String states) {
        this.idContract = Long.valueOf(idContract);
        this.objects = objects;
        this.customer = customer;
        this.executor = executor;
        this.responsible = responsible;
        this.department = department;
        this.states = states;
    }
}
