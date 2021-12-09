package com.example.controllers;

import com.example.entities.CustomerEntity;
import com.example.requests.CustomerRequest;
import com.example.responses.CollectionResponse;
import com.example.responses.CustomerResponse;
import com.example.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public CollectionResponse<CustomerResponse> getCustomers(){
        final List<CustomerResponse> customerResponses = customerService.getCustomerResponses();
        return new CollectionResponse<>(customerResponses);
    }

    @GetMapping(value = "/{id}")
    public CustomerResponse getCustomer(@PathVariable Long id){
        return customerService.getCustomerResponse(id);
    }

    @PostMapping
    public CustomerResponse saveCustomer(@RequestBody CustomerRequest customerRequest){
        final CustomerEntity customerEntity = customerService.saveCustomer(customerRequest);
        return customerService.getCustomerResponse(customerEntity.getId());
    }
}
