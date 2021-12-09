package com.example.exceptions;

public class NoRecordException extends BaseException{
    private String tableName;
    private Long id;

    public NoRecordException(String table, Long id){
        super(String.format("Record is not found: %d", id));
        this.tableName = table;
        this.id = id;
    }
}
