package com.example.webdev.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FullContractModel {
    private int id;
    private String object;
    private int customer;
    private String executor;
    private int responsibleOne;
    private int responsibleTwo;
    private int departmentOne;
    private int departmentTwo;
    private int status;
    private String date;
    private String description;
}
