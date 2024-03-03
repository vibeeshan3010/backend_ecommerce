package com.example.demo.controller;

import com.example.demo.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class StorageController {
    @Autowired
    private StorageService storageService;


    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "File is empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            String publicURL = storageService.uploadFile(file);
            Map<String, String> response = new HashMap<>();
            response.put("publicURL", publicURL);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> response = new HashMap<>();
            response.put("error", "File upload failed");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @GetMapping("/download/{filename}")
//    public  ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String filename){
//        byte[] data =storageService.downloadFile(filename);
//        ByteArrayResource byteArrayResource=new ByteArrayResource(data);
//        return ResponseEntity.ok().contentLength(data.length).header("Content-type","application/octet-stream")
//                .header("Content-disposition","attachment;filename=\"" +filename + "\"").body(byteArrayResource);
//    }
//
//    @DeleteMapping("/delete/{filename}")
//    public  ResponseEntity<String> deleteFile(@PathVariable String filename) {
//        return new ResponseEntity<>(storageService.deleteFile(filename), HttpStatus.OK);
//    }

}
