package com.example.webdev.db.dto;

import lombok.Data;

@Data
public class PersonalDto {
    int id;
    String title;

    public PersonalDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
