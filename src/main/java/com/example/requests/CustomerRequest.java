package com.example.requests;

import lombok.Data;

@Data
public class CustomerRequest {
    private Long id;
    private String firstName;
    private String lastName;
}
