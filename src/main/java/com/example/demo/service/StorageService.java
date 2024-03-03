package com.example.demo.service;

import com.amazonaws.services.s3.AmazonS3Client;

import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;


import java.io.*;
import java.util.UUID;

import static com.amazonaws.services.s3.model.CannedAccessControlList.*;
import static com.amazonaws.services.s3.model.CannedAccessControlList.PublicRead;


@Service
public class StorageService {
    @Value("${application.bucket.name}")
    private String bucketname;



    private final S3Client s3Client;

    public StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }


    public String uploadFile(MultipartFile file) {
        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String key = UUID.randomUUID().toString() + "." + extension;
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            // Perform S3 operation (e.g., putObject)
            s3Client.putObject(
                    PutObjectRequest.builder()
                            .bucket("ecommerce-vibeeeshan")
                            .key(key)  // Specify a unique object key for each object
                            .build(),
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );

            // If successful, you can continue with other logic or return a success response
            String publicURL = generatePublicURL("ecommerce-vibeeeshan", key);
            return publicURL;
        } catch (NoSuchBucketException e) {
            // Handle the exception (e.g., log an error or throw a custom exception)
            e.printStackTrace(); // Log the exception for debugging purposes
            throw new RuntimeException("Error uploading file: NoSuchBucketException");
        } catch (S3Exception e) {
            // Handle other S3-related exceptions
            e.printStackTrace();
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        } catch (IOException e) {
            // Handle IO exceptions
            e.printStackTrace();
            throw new RuntimeException("Error uploading file: " + e.getMessage());
        }

//        return s3Client.utilities().getUrl(GetUrlRequest.builder()
//                        .bucket("ecommerce-vibeeshan")
//                        .key(key)
//                        .build())
//                .toExternalForm();



    }

    public String generatePublicURL(String bucketName, String objectKey) {
        String region = "ap-south-1";  // Replace with your AWS region

        String publicURL = String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, objectKey);
        return publicURL;
    }



    private File  convertMultiPartFile(MultipartFile file){

        File convertedFile=new File(file.getOriginalFilename());
        try (FileOutputStream fileOutputStream=new FileOutputStream(convertedFile)){

        } catch (IOException e) {
            System.out.println("Error converting file " + e);
        }
        return convertedFile;
    }

//    public String deleteFile(String filename) {
//        try {
//            s3Client.deleteObject(DeleteObjectRequest.builder()
//                    .bucket(bucketname)
//                    .key(filename)
//                    .build());
//            return "File Removed: " + filename;
//        } catch (Exception e) {
//            // Handle the exception (e.g., log it)
//            System.out.println("Error deleting file " + filename + ": " + e.getMessage());
//            return "Error deleting file: " + filename;
//        }
//    }

//    public byte[] downloadFile(String fileName) {
//        try {
//            ResponseInputStream<GetObjectResponse> s3Object = s3Client.getObject(b -> b.bucket(bucketname).key(fileName));
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = s3Object.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//
//            return outputStream.toByteArray();
//        } catch (IOException e) {
//            // Handle the exception (e.g., log it)
//            System.out.println("Error downloading file " + fileName + ": " + e.getMessage());
//        }
//
//        return null;
//    }

}
