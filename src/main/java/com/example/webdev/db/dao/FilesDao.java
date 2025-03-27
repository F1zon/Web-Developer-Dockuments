package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "файлы")
public class FilesDao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private int id;

    @Column(name = "file_way")
    private String fileWay;
    @Column(name = "contract")
    private int idContact;
    @Column(name = "name")
    private String name;

    public FilesDao(String fileWay, int idContact, String name) {
        this.fileWay = fileWay;
        this.idContact = idContact;
        this.name = name;
    }
}
