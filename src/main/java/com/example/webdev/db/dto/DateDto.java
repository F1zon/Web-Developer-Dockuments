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
     Date dateStart;
     Date dateEnd;
     String description;
     String stages;
     int contract;
}
