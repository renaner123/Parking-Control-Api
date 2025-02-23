package com.api.parkingcontrol.configuration;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DateConfig {
    
    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    /**
     * 
     * Usado para definir o padrão de data como ISO 8601
     */
    @Bean
    @Primary
    public ObjectMapper objectMapper(){
            JavaTimeModule module = new JavaTimeModule();
            module.addSerializer(LOCAL_DATETIME_SERIALIZER);
            return new ObjectMapper().registerModule(module);
    }
    
}
