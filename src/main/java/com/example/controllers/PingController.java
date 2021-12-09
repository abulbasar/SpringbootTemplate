package com.example.controllers;

import com.example.responses.GenericResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ping")
public class PingController {

    @GetMapping
    public GenericResponse ping(){
        return new GenericResponse();
    }
}
