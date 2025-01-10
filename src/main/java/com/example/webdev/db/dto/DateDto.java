package com.example.webdev.db.dto;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@Data
public class DateDto {

     public DateDto(int id, String description, int contract, String dateStart) {
          this.id = id;
          this.description = description;
          this.contract = contract;
          this.dateStart = dateStart;
     }

     int id;
     String description;
     int contract;
     String dateStart;
}
