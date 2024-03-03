package com.example.demo.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversionConfig {

    @Bean
    public StringToBucketConverter stringToBucketConverter() {
        return new StringToBucketConverter();
    }
}
