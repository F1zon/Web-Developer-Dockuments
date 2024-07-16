package com.example.webdev.repository;

import com.example.webdev.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

    /**
     * Укороченный запрос для главной страницы
     * @return
     */
    @Query(value = "SELECT д.id_contract, д.objects, з.title, д.executor, с.fio, ст.title FROM договоры д " +
            "JOIN заказчики з ON д.customer = з.id_customer " +
            "JOIN сотрудники с ON д.responsible = с.id_personal " +
            "JOIN статусы ст ON д.states = ст.id_status;",
                nativeQuery = true)
    List<String> findAllContractsJoin();
}
