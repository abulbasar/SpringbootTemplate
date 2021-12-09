package com.example.responses;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CollectionResponse <T>{
    private List<T> records;

    public CollectionResponse(List<T> records) {
        this.records = records;
    }
}
