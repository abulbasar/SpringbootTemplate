package com.example.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.TimeZone;

public class JsonUtils {
    public final static XmlMapper xmlMapper = XmlMapper
            .builder()
            .defaultUseWrapper(false)
            .build();

    public final static ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setDateFormat(new StdDateFormat().withTimeZone(TimeZone.getDefault()));
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(JsonReadFeature.ALLOW_MISSING_VALUES.mappedFeature(), true);
        objectMapper.registerModule(new JavaTimeModule());
    }

}
