package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.response.AddProductResponse;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST}, allowedHeaders = "*")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    // RESTful API methods for Retrieval operations
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productService.listAll();
    }


    @PostMapping(value = "/addproducts")
    public ResponseEntity<AddProductResponse> addProduct(@RequestBody Product product) {

        LocalDateTime now = LocalDateTime.now();


        String productcode = product.getProductcode();
        System.out.println("Product Code" + productcode);
        boolean productExist = productRepository.existsByproductcode(productcode);
        if (productExist) {
            String redirectURL = "/addProduct";
            return ResponseEntity.ok(new AddProductResponse("1", "Product Exist"));
        } else {
            Product newproduct = productService.save(product);
            String redirectURL = "/addProduct";
            if (newproduct != null) {
                return ResponseEntity.ok(new AddProductResponse("2", "Saved Successfully"));
            } else {
               redirectURL = "/addProduct";
                return ResponseEntity.ok(new AddProductResponse("3.", "Failed"));
            }
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct (@PathVariable("id") Integer id){
        productService.delete(id);
        return ResponseEntity.ok("Product with id " + id + " deleted successfully");
    }

}

        // RESTful API method for Create operation
//    @PostMapping("/addproduct")
//    public AddProductResponse addProduct(@RequestBody Product product) {
//        if(product.getCategory()==null) {
//            System.out.println("category is null");
//        }
//        boolean productExists = productRepository.existsByproductCode(product.getProductCode());
//        if(productExists){
//            return new AddProductResponse("Product code exists", null);
//        }
//            Product savedProduct = service.save(product);
//            System.out.println("savedProduct name" + savedProduct.getProductName());
//            System.out.println("savedProduct category" + savedProduct.getCategory());
//            return new AddProductResponse("Product added successfully",product);
//
//
//    }
//

        // RESTful API method for Update operation
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<String> editProduct(@PathVariable("id") Integer id, @RequestBody Product product) {
//        try {
//            Product existProduct = service.get(id);
//            existProduct.getCategory().getCategoryName();
//            existProduct.setProductName(product.getProductName());
//            existProduct.setPrice(product.getPrice());
//            service.save(existProduct);
//            return ResponseEntity.ok("Product with id " + id + " updated successfully");
//        } catch (NoSuchElementException e) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

        // RESTful API method for Delete operation




