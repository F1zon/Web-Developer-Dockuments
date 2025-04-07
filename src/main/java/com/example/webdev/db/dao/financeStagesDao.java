package com.example.webdev.db.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="finance_stages")
public class financeStagesDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "finance_id")
    private int finance_id;
    @Column(name = "stage_name")
    private String stage_name;
    @Column(name = "start_date_work")
    private String start_date_work;
    @Column(name = "completion_date")
    private String completion_date;
    @Column(name = "amount_stage")
    private String amount_stage;
    @Column(name = "account_number")
    private String account_number;
    @Column(name = "account_date")
    private String account_date;
    @Column(name = "planned_payment_date")
    private String planned_payment_date;
    @Column(name = "actual_payment_date")
    private String actual_payment_date;
    @Column(name = "act_number")
    private String act_number;
    @Column(name = "act_date")
    private String act_date;
    @Column(name = "act_amount")
    private String act_amount;
}
