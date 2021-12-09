package com.example.responses;

import lombok.Data;

@Data
public class CustomerResponse extends BaseResponse{
    private String firstName;
    private String lastName;
}
