package com.example.webdev.model;

import lombok.Data;

@Data
public class StatusModel {
    int id;
    String title;

    public StatusModel(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
