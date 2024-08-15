package com.example.webdev.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateModel {
    String dateStart;
    String dateEnd;
    String description;
    String stage;
    int idContract;
}
