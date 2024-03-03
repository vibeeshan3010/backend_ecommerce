package com.example.demo.response;

public class AddProductResponse {

    private String status;
    private String  message;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public AddProductResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }
}