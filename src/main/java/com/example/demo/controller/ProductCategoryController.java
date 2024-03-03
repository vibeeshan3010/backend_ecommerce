package com.example.demo.controller;

import com.example.demo.domain.ProductCategory;
import com.example.demo.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product")
public class ProductCategoryController{
    @Autowired
    private ProductCategoryService productService;

    @GetMapping("/getproductcategory")
    public ResponseEntity<List<ProductCategory>> getallproducts(){
         List<ProductCategory> productCategory=productService.listAll();
        return ResponseEntity.ok(productCategory);
    }

}