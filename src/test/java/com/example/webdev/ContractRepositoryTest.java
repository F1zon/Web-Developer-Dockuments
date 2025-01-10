package com.example.webdev;

import com.example.webdev.db.dao.CustomerDao;
import com.example.webdev.repository.ContractRepository;
import com.example.webdev.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ContractRepositoryTest {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Test
    public void findByIdCustomer() {
        Assertions.assertNotNull(contractRepository.findById(2));
    }

}
