package com.example.webdev.db.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CustomerDto {
    int id;
    String title;

    public CustomerDto(int id, String title) {
        this.id = id;
        this.title = title;
    }
}
