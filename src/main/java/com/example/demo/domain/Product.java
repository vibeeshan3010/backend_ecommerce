package com.example.demo.domain;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productcode;
    private String productbrand;
    private String productdescription;
    private String productname;

    public String getProductimgdata() {
        return productimgdata;
    }

    public void setProductimgdata(String productimgdata) {
        this.productimgdata = productimgdata;
    }

    @Column(name = "productimgdata")
    private String productimgdata;


    public String getProductcode() {
        return productcode;
    }

    public void setProductcode(String productcode) {
        this.productcode = productcode;
    }

    public String getProductbrand() {
        return productbrand;
    }

    public void setProductbrand(String productbrand) {
        this.productbrand = productbrand;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public BigDecimal getProductprice() {
        return productprice;
    }

    public void setProductprice(BigDecimal productprice) {
        this.productprice = productprice;
    }

    public String getProductcategory() {
        return productcategory;
    }

    public void setProductcategory(String productcategory) {
        this.productcategory = productcategory;
    }

    private int productquantity;
    private BigDecimal productprice;

    private String productcategory;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    // Getters and setters


}