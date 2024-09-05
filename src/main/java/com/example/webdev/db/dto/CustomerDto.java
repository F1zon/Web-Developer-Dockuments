package com.example.webdev.db.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CustomerDto {
    int id;
    String name;

    public CustomerDto(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
