package com.example.responses;

import lombok.Data;

@Data
public class NewsItem {
    private String title;
    private String description;
    private String link;
    private String image;
    private String guid;
    private String pubDate;
}
