package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ComponentContractDto {
    private int id;
    private int customerId;
    private int responsibleId;
    private int responsible2Id;
    private int status;
    private String objectTitle;
    private String executor;

    public ComponentContractDto(int id, String objectTitle, int customerId, String executor, int responsibleId, int responsible2Id, int status) {
        this.id = id;
        this.customerId = customerId;
        this.responsibleId = responsibleId;
        this.responsible2Id = responsible2Id;
        this.status = status;
        this.objectTitle = objectTitle;
        this.executor = executor;
    }
}
