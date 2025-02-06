package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StageDto {
    private int id;
    private String dateStartStage;
    private String descriptionStage;
    private String dateEndStage;
    private int contract;

    @Override
    public String toString() {
        return "StageDto [id=" + id +
                ", dateStartStage=" + dateStartStage +
                ", descriptionStage=" + descriptionStage +
                ", dateEndStage=" + dateEndStage;
    }
}
