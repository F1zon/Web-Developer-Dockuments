package com.example.webdev.model;

import lombok.Data;

@Data
public class PersonalModel {
    int id;
    String title;

    public PersonalModel(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
