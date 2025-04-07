package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class financeContractDto {
    int id;
    String date_formation;
    String number;
    String item;
    String responsible_department;
    String amount;
    int contract_id;
}
