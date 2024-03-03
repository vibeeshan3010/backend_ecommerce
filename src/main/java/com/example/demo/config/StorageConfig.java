package com.example.demo.config;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

@Configuration
public class StorageConfig {

    @Value("${spring.cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${spring.cloud.aws.credentials.secret-key}")
    private String accessSecret;

    @Value("${spring.cloud.aws.region.static}")
    private String awsRegion;  // Change the type to String

    @Bean
    public S3Client s3Client() {
        AwsCredentials awsCredentials = AwsBasicCredentials.create(accessKey, accessSecret);

        S3Client s3Client = S3Client.builder()
                .region(Region.of(awsRegion))
                .credentialsProvider(StaticCredentialsProvider.create(awsCredentials))
                .build();

        // You can test the S3 client by listing buckets
        ListBucketsResponse listBucketsResponse = s3Client.listBuckets();
        System.out.println("Buckets: " + listBucketsResponse.buckets());

        return s3Client;
    }

}
