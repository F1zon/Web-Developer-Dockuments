package com.example.webdev.controllers;

import com.example.webdev.db.model.ContractModel;
import com.example.webdev.db.model.DateModel;
import com.example.webdev.db.model.FileModel;

public class TablesModels {
    ContractModel contractModel;
    DateModel dateModel;
//    FileModel fileModel;

    TablesModels() {}

    TablesModels (ContractModel model) {
        this.contractModel = model;
    }

    public void setContractModel(ContractModel model) {
        this.contractModel = model;
    }

    public void setDateModel(DateModel model) {
        this.dateModel = model;
    }

//    public void setFileModel(FileModel model) {
//        this.fileModel = model;
//    }


    public ContractModel getContractModel() {
        return contractModel;
    }

    public DateModel getDateModel() {
        return dateModel;
    }
}



