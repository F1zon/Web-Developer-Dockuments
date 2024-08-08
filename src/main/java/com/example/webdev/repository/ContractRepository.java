package com.example.webdev.repository;

import com.example.webdev.model.Contract;
import com.example.webdev.model.CustomerModel;
import com.example.webdev.model.PersonalModel;
import com.example.webdev.model.StatusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Integer> {

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

    /**
     * Обновление информации по документа (Put в таблицу)
     * @param obj String Объект
     * @param cus int Заказчик
     * @param exe String Исполнитель (Компания)
     * @param files int Файлы
     * @param res int Исполнитль (Персонал)
     * @param res2 int Исполнитель 2 (Персонал)
     * @param dat int Даты по договору
     * @param st int Статус договора
     * @param id int id изменяемого договора
     */
    @Modifying
    @Query("update Contract c set c.objects = ?1," +
            "c.executor = ?2," +
            "c.files = ?3," +
            "c.responsible = ?4," +
            "c.responsible2 = ?5," +
            "c.dates = ?6," +
            "c.states = ?7 where c.idContract = ?8")
    void setContractById(String obj, int cus, String exe, int files, int res, int res2, int dat, int st, int id);

    @Query(value = "select id_customer, title from заказчики", nativeQuery = true)
    List<String> findAllCustomers();

    @Query(value = "select id_personal, fio from сотрудники", nativeQuery = true)
    List<String> findAllPersonals();

    @Query(value = "select id_status, title from статусы", nativeQuery = true)
    List<String> findAllStatus();
}
