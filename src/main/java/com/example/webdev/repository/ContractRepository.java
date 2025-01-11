package com.example.webdev.repository;

import com.example.webdev.db.dao.ContractDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContractRepository extends JpaRepository<ContractDao, Integer> {

    /**
     * Укороченный запрос для главной страницы
     * @return Укороченный список строк из таблицы Должности
     */
    @Query(value = """
            SELECT д.id_contract, д.objects, з.name, д.executor, с.name, о.name, ст.name FROM договоры д
            JOIN заказчики з ON д.customer = з.id
            \tJOIN сотрудники с ON д.responsible = с.id
            \tJOIN отделы о ON д.responsible = о.id
            \tJOIN статусы ст ON д.states = ст.id;""",
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

//    Запросы для конкретного контракта
//    Заказчик
    @Query(value = "select з.name from договоры д join заказчики з on д.customer = з.id where д.id_contract = ?1", nativeQuery = true)
    String getCustomerById(int id);

//    Ответсвенные
    @Query(value = """
            select id, name, department from сотрудники с
            join договоры д on с.id = д.responsible where д.id_contract = ?1
            """, nativeQuery = true)
    String getResponsibleById(int id);

    @Query(value = """
            select id, name, department from сотрудники с
            join договоры д on с.id = д.responsible_2 where д.id_contract = ?1
            """, nativeQuery = true)
    String getResponsible2ById(int id);

    @Query(value = """
            select д.id_contract, с.name from договоры д
            join статусы с on д.states = с.id where д.id_contract = ?1
            """, nativeQuery = true)
    String getStatusById(int id);

    @Query(value = """
                select id_dat,description,contract,date_start from даты where contract = ?1
                """, nativeQuery = true)
    String getDatesById(int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = """
                delete from даты where contract = ?1;

                delete from договоры where id_contract in (
                    select id_contract from договоры left outer join даты on договоры.id_contract = даты.contract
                                       where даты.contract is null
                );
                """, nativeQuery = true)
    void deleteContractAndDateById(long id);

    @Query(value = """
        select * from договоры where id_contract = ?1
        """, nativeQuery = true)
    String findByIdContract(int id);

    @Modifying
    @Query(value = """
        update договоры set objects = ?1, customer = ?2, executor = ?3, responsible = ?4, responsible_2 = ?5, states = ?6 where id_contract = ?7
        """, nativeQuery = true)
    void updateContract(String obj, int customer, String executor, int responsible, int responsible2, int states, int id);
}
