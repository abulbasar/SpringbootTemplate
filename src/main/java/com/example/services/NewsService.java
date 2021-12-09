package com.example.services;

import com.example.entities.NewsItemEntity;
import com.example.repositories.NewsArticleRepository;
import com.example.responses.CollectionResponse;
import com.example.responses.NewsItem;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.common.JsonUtils.objectMapper;
import static com.example.common.JsonUtils.xmlMapper;

@Service
@Slf4j
public class NewsService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private NewsArticleRepository newsItemRepository;

    public CollectionResponse<NewsItem> getNewsItems(){
        final CollectionResponse<NewsItem> response = new CollectionResponse<>();
        final List<NewsItem> newsItems = new ArrayList<>();
        newsItemRepository.findAll().forEach(newsItemEntity ->
                newsItems.add(toNewsItem(newsItemEntity)));
        response.setRecords(newsItems);
        return response;
    }

    public NewsItem toNewsItem(NewsItemEntity entity){
        final NewsItem newsItem = new NewsItem();
        BeanUtils.copyProperties(entity, newsItem);
        return newsItem;
    }

    public void crawlRssFeeds(String url){
        try {
            final RssFeed rssFeed = readRssFeed(url);
            final List<NewsItem> newsItems = getNewsItems(rssFeed);
            final List<NewsItemEntity> newsItemEntities = newsItems.stream()
                    .map(this::toNewsItemEntity).collect(Collectors.toList());
            newsItemRepository.saveAll(newsItemEntities);
        } catch (Exception e) {
            log.error("Failed to process rss feed: " + url, e);
        }
    }

    private List<NewsItem> getNewsItems(RssFeed rssFeed){
        final List<Item> items = rssFeed.getChannel().getItems();
        return items.stream().map(this::toNewsItem).collect(Collectors.toList());
    }

    private NewsItem toNewsItem(Item item){
        final NewsItem newsItem = new NewsItem();
        BeanUtils.copyProperties(item, newsItem);
        return newsItem;
    }

    public CollectionResponse<NewsItem> search(String q) {
        final List<NewsItemEntity> itemEntities = newsItemRepository
                .findByDescriptionContaining(q);
        final List<NewsItem> records = itemEntities.stream().map(this::toNewsItem).collect(Collectors.toList());
        return new CollectionResponse<>(records);
    }

    @Data
    static class RssFeed{
        private String version;
        private Channel channel;
    }

    @Data
    static class Channel{
        private String title;
        private String language;
        private String description;

        @JsonProperty("item")
        private List<Item> items;
    }

    @Data
    static class Item{
        private String title;
        private String description;
        private String link;
        private String image;
        private String guid;

        @JsonProperty("pubDate")
        private String pubDate;
    }

    private RssFeed readRssFeed(String url) throws JsonProcessingException {
        final ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        final HttpStatus statusCode = responseEntity.getStatusCode();
        if(responseEntity.hasBody()){
            final String content = responseEntity.getBody();
            final JsonNode jsonNode = xmlMapper.readTree(content);
            final RssFeed rssFeed = objectMapper.treeToValue(jsonNode, RssFeed.class);
            return rssFeed;
        }
        return null;
    }

    private NewsItemEntity toNewsItemEntity(NewsItem item){
        final NewsItemEntity itemEntity = new NewsItemEntity();
        BeanUtils.copyProperties(item, itemEntity);
        return itemEntity;
    }
}
