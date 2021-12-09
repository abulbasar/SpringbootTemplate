package com.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Transient
    public static final Sort SORT_BY_CREATED_AT_DESC =
            Sort.by(Sort.Direction.DESC, "createdAt");

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
