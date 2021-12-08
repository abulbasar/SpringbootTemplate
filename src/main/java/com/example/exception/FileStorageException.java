package com.example.exception;

public class FileStorageException extends RuntimeException{
    public FileStorageException(String message){
        super(message);
    }
    public FileStorageException(String message, Throwable th){
        super(message, th);
    }
}
