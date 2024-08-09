package com.example.webdev.model;

import lombok.Data;

@Data
public class FilesModel {
    private String fileName;
    private String idContact;

    public FilesModel(String idContact, String fileName) {
        this.idContact = idContact;
        this.fileName = fileName;
    }
}
