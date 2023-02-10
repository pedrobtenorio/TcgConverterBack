package com.tenorio.pokemontcg.config;

import com.tenorio.pokemontcg.service.ExcelGenerator;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder();
    }

    @Bean
    public ExcelGenerator excelGenerator() {
        return new ExcelGenerator();
    }

}

