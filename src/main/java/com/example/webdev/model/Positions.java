package com.example.webdev.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "должности")
public class Positions {
    @Id
    @Column(name = "id_positions")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPositions;

    @Column(name = "title")
    private String title;
}
