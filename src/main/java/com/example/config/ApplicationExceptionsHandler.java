package com.example.config;

import com.example.exceptions.BaseException;
import com.example.responses.GenericResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;

import javax.servlet.ServletException;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionsHandler {
    @ExceptionHandler(value = {
            BaseException.class,
            NullPointerException.class,
            HttpMessageNotReadableException.class,
            MultipartException.class,
            ServletException.class
    })
    public ResponseEntity<GenericResponse> handleUserServiceExceptionV2(Exception ex, WebRequest request){
        final String message = ex.getMessage();
        final GenericResponse genericResponse = new GenericResponse();
        genericResponse.setMessage(message);
        genericResponse.setStatus(Constants.STATUS_Failed);
        return new ResponseEntity<>(genericResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
