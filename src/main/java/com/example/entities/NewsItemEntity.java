package com.example.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = NewsItemEntity.TABLE_NAME)
public class NewsItemEntity extends BaseEntity{

    public final static String TABLE_NAME = "news_article";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(columnDefinition="TEXT")
    private String description;

    private String link;
    private String image;
    private String guid;
    private String pubDate;
}
