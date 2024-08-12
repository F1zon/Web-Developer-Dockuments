package com.example.webdev.db.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "файлы")
public class FilesDao {
    @Id
    @Column(name = "id_file")
    private int id;

    @Column(name = "file_way")
    private String fileName;
    @Column(name = "contract")
    private int idContact;
}
