package com.example.jpa.config;

import com.example.jpa.dto.converter.JpaDtoConverter;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JpaConfig {
    
    @PersistenceContext
    public EntityManager em;
    
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
    
    @Bean
    public JpaDtoConverter jpaDtoConverter() {
        return new JpaDtoConverter();
    }
}
