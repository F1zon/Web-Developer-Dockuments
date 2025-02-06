package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "этапы")
public class StageDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date_start_stage")
    private String dateStartStage;

    @Column(name = "description")
    private String descriptionStage;

    @Column(name = "date_end_stage")
    private String dateEndStage;

    @Column(name = "contract")
    private int contract;

    public StageDao(String dateStartStage, String descriptionStage, String dateEndStage, int contract) {
        this.dateStartStage = dateStartStage;
        this.descriptionStage = descriptionStage;
        this.dateEndStage = dateEndStage;
        this.contract = contract;
    }

    public StageDao(int id, String dateStartStage, String descriptionStage, String dateEndStage, int contract) {
        this.id = id;
        this.dateStartStage = dateStartStage;
        this.descriptionStage = descriptionStage;
        this.dateEndStage = dateEndStage;
        this.contract = contract;
    }
}
