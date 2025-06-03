package com.example.webdev.db.dto;

import lombok.Data;

@Data
public class SmallContractDto {
    int idContract;
    String objects;
    String customer;
    String executor;
    String responsible;
    String department;
    String states;
    String dateStart;
    String dateEnd;

    public SmallContractDto(String idContract, String objects, String customer, String executor, String responsible, String department, String states, String dateStart, String dateEnd) {
        this.idContract = Integer.parseInt(idContract);
        this.objects = objects;
        this.customer = customer;
        this.executor = executor;
        this.responsible = responsible;
        this.department = department;
        this.states = states;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public SmallContractDto(String idContract, String objects, String customer, String executor, String responsible, String department, String states) {
        this.idContract = Integer.parseInt(idContract);
        this.objects = objects;
        this.customer = customer;
        this.executor = executor;
        this.responsible = responsible;
        this.department = department;
        this.states = states;
    }
}
