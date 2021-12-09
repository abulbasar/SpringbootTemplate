package com.example.services;

import com.example.entities.CustomerEntity;
import com.example.exceptions.NoRecordException;
import com.example.repositories.CustomerRepository;
import com.example.requests.CustomerRequest;
import com.example.responses.CustomerResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity getCustomer(Long id) {
        final Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        if(!customerEntity.isPresent()){
            throw new NoRecordException(CustomerEntity.TABLE_NAME, id);
        }
        return customerEntity.get();
    }
    public CustomerResponse getCustomerResponse(Long id){
        final CustomerEntity customerEntity = getCustomer(id);
        return toCustomerResponse(customerEntity);
    }

    public CustomerEntity saveCustomer(CustomerRequest customerRequest) {
        final CustomerEntity customerEntity = new CustomerEntity();
        BeanUtils.copyProperties(customerRequest, customerEntity);
        customerRepository.save(customerEntity);
        return customerEntity;
    }


    public CustomerResponse toCustomerResponse(CustomerEntity customerEntity){
        final CustomerResponse customerResponse = new CustomerResponse();
        BeanUtils.copyProperties(customerEntity, customerResponse);
        return customerResponse;
    }


    public List<CustomerEntity> getCustomerEntities(){
        return customerRepository.findAll();
    }

    public List<CustomerResponse> getCustomerResponses(){
        return getCustomerEntities().stream()
                .map(this::toCustomerResponse).collect(Collectors.toList());
    }
}
