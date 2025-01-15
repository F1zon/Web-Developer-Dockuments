package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
@ToString
public class FullContractDto {
    private int id;
    private String object;
    private int customer;
    private String executor;
    private int responsibleOne;
    private int departmentOne;
    private int responsibleTwo;
    private int departmentTwo;
    private int status;
    private String date;
    private String description;

    public FullContractDto(int id, String object, int customer,
                           String executor, int responsibleOne,
                           int departmentOne, int responsibleTwo,
                           int departmentTwo, int status,
                           String date, String description) {
        this.id = id;
        this.object = object;
        this.customer = customer;
        this.executor = executor;
        this.responsibleOne = responsibleOne;
        this.departmentOne = departmentOne;
        this.responsibleTwo = responsibleTwo;
        this.departmentTwo = departmentTwo;
        this.status = status;
        this.date = date;
        this.description = description;
    }
}
