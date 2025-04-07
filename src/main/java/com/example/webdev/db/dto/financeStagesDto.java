package com.example.webdev.db.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class financeStagesDto {
    int id;
    int finance_id;
    String stage_name;
    String start_date_work;
    String completion_date;
    String amount_stage;
    String account_number;
    String account_date;
    String planned_payment_date;
    String actual_payment_date;
    String act_number;
    String act_date;
    String act_amount;
}
