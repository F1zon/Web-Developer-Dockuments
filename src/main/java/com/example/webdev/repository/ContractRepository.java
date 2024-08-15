package com.example.webdev.repository;

import com.example.webdev.db.dao.ContractDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractDao, Integer> {

    /**
     * Укороченный запрос для главной страницы
     * @return Укороченный список строк из таблицы Должности
     */
    @Query(value = "SELECT д.objects, з.title, д.executor, с.fio, о.title, ст.title FROM договоры д\n" +
            "JOIN заказчики з ON д.customer = з.id_customer\n" +
            "\tJOIN сотрудники с ON д.responsible = с.id_personal\n" +
            "\tJOIN отделы о ON д.responsible = о.id_departments\n" +
            "\tJOIN статусы ст ON д.states = ст.id_status;",
                nativeQuery = true)
    List<String> findAllContractsJoin();

    @Query(value = "select id_customer, title from заказчики", nativeQuery = true)
    List<String> findAllCustomers();

    @Query(value = "select id_personal, fio from сотрудники", nativeQuery = true)
    List<String> findAllPersonals();

    @Query(value = "select id_status, title from статусы", nativeQuery = true)
    List<String> findAllStatus();

    @Query(value = "SELECT MAX(id_contract) FROM договоры", nativeQuery = true)
    int getNextContactId();

//   Запросы для получения Сотрудников, Заказчиков и Статуса по названию
    @Query(value = "select id_personal from сотрудники where fio = ?1", nativeQuery = true)
    int findPersonalByFio(String name);

    @Query(value = "select id_customer from заказчики where title = ?1", nativeQuery = true)
    int findCustomerByTitle(String title);

    @Query(value = "select id_status from статусы where title = ?1", nativeQuery = true)
    int findStageByTitle(String title);
}
