package com.example.demo.utils;

import org.springframework.core.convert.converter.Converter;
import software.amazon.awssdk.services.s3.model.Bucket;


public class StringToBucketConverter implements Converter<String, Bucket> {
    @Override
    public Bucket convert(String source) {
        return Bucket.builder().name(source).build();
    }
}
