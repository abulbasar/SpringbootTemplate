package com.example.responses;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseResponse {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
