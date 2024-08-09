package com.example.webdev.model;

import lombok.Data;

@Data
public class DateModel {
    private String dateStart;
    private String dateEnd;
    private String description;
    private String stage;
    private int idContract;

    public DateModel(int idContract, String stage, String description, String dateEnd, String dateStart) {
        this.idContract = idContract;
        this.stage = stage;
        this.description = description;
        this.dateEnd = dateEnd;
        this.dateStart = dateStart;
    }
}
