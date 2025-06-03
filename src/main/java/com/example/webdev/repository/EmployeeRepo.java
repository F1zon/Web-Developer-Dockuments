package com.example.webdev.repository;

import com.example.webdev.db.dao.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    Optional<Employee> findByName(String name);

    @Query(value = """
            SELECT r.title FROM сотрудники s\s
            	JOIN отделы d ON s.department = d.id\s
            	JOIN роли r ON d.role = r.id_roles WHERE s.department = 1
            """, nativeQuery = true)
    String getRoleByDepartmentId(int id);
}
