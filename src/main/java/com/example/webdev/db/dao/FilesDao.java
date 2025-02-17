package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
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
    private String fileName;
    @Column(name = "contract")
    private int idContact;
    @Column(name = "name")
    private String name;

    public FilesDao(String fileName, int idContact, String name) {
        this.fileName = fileName;
        this.idContact = idContact;
        this.name = name;
    }
}
