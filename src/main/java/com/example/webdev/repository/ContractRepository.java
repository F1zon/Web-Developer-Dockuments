package com.example.webdev.repository;

import com.example.webdev.db.dao.ContractDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractDao, Integer> {

    /**
     * Укороченный запрос для главной страницы
     * @return Укороченный список строк из таблицы Должности
     */
    @Query(value = "SELECT д.objects, з.name, д.executor, с.name, о.name, ст.name FROM договоры д\n" +
            "JOIN заказчики з ON д.customer = з.id\n" +
            "\tJOIN сотрудники с ON д.responsible = с.id\n" +
            "\tJOIN отделы о ON д.responsible = о.id\n" +
            "\tJOIN статусы ст ON д.states = ст.id;",
                nativeQuery = true)
    List<String> findAllContractsJoin();

    @Query(value = "select id, name from заказчики", nativeQuery = true)
    List<String> findAllCustomers();

    @Query(value = "select id, name, department from сотрудники", nativeQuery = true)
    List<String> findAllPersonals();

    @Query(value = "select id, name from статусы", nativeQuery = true)
    List<String> findAllStatus();

    @Query(nativeQuery = true, value = "select id, name from отделы")
    List<String> findAllDepartments();

    @Query(value = "SELECT MAX(id_contract) FROM договоры", nativeQuery = true)
    int getNextContactId();

//   Запросы для получения Сотрудников, Заказчиков и Статуса по названию
    @Query(value = "select id from сотрудники where name = ?1", nativeQuery = true)
    int findPersonalByFio(String name);

    @Query(value = "select id from заказчики where name = ?1", nativeQuery = true)
    int findCustomerByTitle(String title);

    @Query(value = "select id from статусы where name = ?1", nativeQuery = true)
    int findStageByTitle(String title);

    @Query(value = "SELECT MAX(id_contract) + 1 FROM договоры", nativeQuery = true)
    int getNexValId();
}
