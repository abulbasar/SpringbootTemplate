package com.example.exceptions;

import com.fasterxml.jackson.databind.ser.Serializers;

public class BaseException extends RuntimeException{
    public BaseException(){

    }
    public BaseException(String message){
        super(message);
    }
    public BaseException(String message, Throwable throwable){
        super(message, throwable);
    }

}
