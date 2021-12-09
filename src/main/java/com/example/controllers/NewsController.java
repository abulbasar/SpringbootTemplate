package com.example.controllers;

import com.example.responses.CollectionResponse;
import com.example.responses.GenericResponse;
import com.example.responses.NewsItem;
import com.example.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping
    public CollectionResponse<NewsItem> getNewsItems(){
        return newsService.getNewsItems();
    }

    @GetMapping(value = "/search")
    public CollectionResponse<NewsItem> search(@RequestParam(name = "q", required = true)String q){
        return newsService.search(q);
    }

    @GetMapping(value = "/crawl")
    public GenericResponse crawl(@RequestParam(name = "url", required = true)String url){
        newsService.crawlRssFeeds(url);
        return new GenericResponse();
    }
}
