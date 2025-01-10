package com.example.webdev;

import com.example.webdev.db.dao.CustomerDao;
import com.example.webdev.repository.ContractRepository;
import com.example.webdev.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WebDevApplicationTests {
    @Autowired
    private ContractRepository contractRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void getCustomerByIdDB() {
        Optional<CustomerDao> customerDao = customerRepository.findById(2);
        assertNotNull(customerDao);
    }

}
