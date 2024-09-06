package com.example.webdev.db.dto;

import lombok.Data;

@Data
public class PersonalDto {
    int id;
    String title;
    int departmentId;

    public PersonalDto(int id, String title, int departmentId) {
        this.id = id;
        this.title = title;
        this.departmentId = departmentId;
    }
}
