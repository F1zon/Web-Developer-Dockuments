package com.example.webdev.db.dto;

import lombok.Data;

@Data
public class StatusDto {
    int id;
    String title;

    public StatusDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
