package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ScheduledService {

    @Autowired
    NewsService newsService;

    final static int INTERVAL = 10000;

    @Scheduled(fixedDelay = INTERVAL, initialDelay = INTERVAL)
    public void crawNewsFeeds(){
        final List<String> targets = Arrays.asList("https://economictimes.indiatimes.com/rssfeedsdefault.cms");
        for (String target : targets) {
            //newsService.crawlRssFeeds(target);
        }
    }
}
