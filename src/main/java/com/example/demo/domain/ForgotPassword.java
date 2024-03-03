package com.example.demo.domain;

public class ForgotPassword {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ForgotPassword(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
