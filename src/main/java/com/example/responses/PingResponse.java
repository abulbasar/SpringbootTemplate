package com.example.responses;

import lombok.Data;

@Data
public class PingResponse {
    private String status;
    private Long timestamp;
    private Long counter;

}
