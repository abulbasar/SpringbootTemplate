package com.example.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.format.DateTimeFormatter;
import java.util.TimeZone;


@Configuration
public class JacksonConfiguration {

    public static final String dateFormat = "yyyy-MM-dd";
    public static final String dateTimeFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    @Bean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){

        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder()
                .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .timeZone(TimeZone.getTimeZone("UTC"))
                .simpleDateFormat(dateTimeFormat)
                .featuresToEnable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
                .serializers(
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(dateFormat)),
                        new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(dateTimeFormat))

                );
        return builder;
    }
}
