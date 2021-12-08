package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Component
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

    @Autowired
    private RequestInterceptor requestInterceptor = null;

    @Override
    public void addInterceptors(@Lazy InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor);
    }

    @Override
    public void configureContentNegotiation( ContentNegotiationConfigurer configurer ) {
        configurer
                .defaultContentType( MediaType.APPLICATION_JSON )
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("json", MediaType.APPLICATION_JSON)
        ;

    }

}
