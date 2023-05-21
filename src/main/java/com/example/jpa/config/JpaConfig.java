package com.example.jpa.config;

import com.example.jpa.dto.converter.JpaDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
    @Bean
    public JpaDtoConverter jpaDtoConverter() {
        return new JpaDtoConverter();
    }
}
