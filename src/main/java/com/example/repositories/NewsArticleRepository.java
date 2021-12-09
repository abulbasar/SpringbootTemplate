package com.example.repositories;

import com.example.entities.NewsItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NewsArticleRepository extends CrudRepository<NewsItemEntity, Long> {
    List<NewsItemEntity> findByDescriptionContaining(String q);
}
