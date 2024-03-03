package com.example.demo.service;

import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@Transactional
@CrossOrigin
public class ProductService {

    @Autowired
    private ProductRepository productRepository;



    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        System.out.println("Product name" +product.getProductname());
        System.out.println("Product Price" +product.getProductprice());
        System.out.println("Product Desc" +product.getProductdescription());
        System.out.println("Product Code" +product.getProductcode());
        System.out.println("Product brand" +product.getProductbrand());
        System.out.println("Product quan" +product.getProductquantity());
        System.out.println("Product category" +product.getProductcategory());
        System.out.println("Product image" +product.getProductimgdata());
        if(!productRepository.existsByproductcode(product.getProductcode())) {
            product.setProductname(product.getProductname());
            product.setProductcode(product.getProductcode());
            product.setProductquantity(product.getProductquantity());
            product.setProductcategory(product.getProductcategory());

            product.setProductbrand(product.getProductbrand());
            product.setProductimgdata(product.getProductimgdata());
            product.setProductdescription(product.getProductdescription());
            product.setProductprice(product.getProductprice());
            product.setProductbrand(product.getProductbrand());


            productRepository.save(product);
        }else {
            System.out.println("Product Exists");
        }
        return product;
    }

    public Product get(Integer id) {
        return productRepository.findById(id).get();
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }


}
