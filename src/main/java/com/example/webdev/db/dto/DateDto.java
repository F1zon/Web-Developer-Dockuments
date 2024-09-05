package com.example.webdev.db.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateDto {
     int id;
     String dateStart;
     String dateEnd;
     String description;
     String stages;
     int contract;
}
