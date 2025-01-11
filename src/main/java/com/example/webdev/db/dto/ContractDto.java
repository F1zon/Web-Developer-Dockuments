package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Data
public class ContractDto {
    private int id;
    private int customerId;
    private int responsibleId;
    private int responsible2Id;
    private int statesTitle;
    private String objectTitle;
    private String executor;

    public ContractDto(int id, String objectTitle, int customerId, String executor, int responsibleId, int responsible2Id, int statesTitle) {
        this.id = id;
        this.customerId = customerId;
        this.responsibleId = responsibleId;
        this.responsible2Id = responsible2Id;
        this.statesTitle = statesTitle;
        this.objectTitle = objectTitle;
        this.executor = executor;
    }
}
