package com.example.webdev.model;

import lombok.Data;

@Data
public class CustomerModel {
    int id;
    String title;

    public CustomerModel(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
