package com.example.repositories;

import com.example.entities.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    List<CustomerEntity> findByFirstName(String FirstName);
    List<CustomerEntity> findAll();
}
