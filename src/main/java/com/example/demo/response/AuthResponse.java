package com.example.demo.response;

import com.example.demo.domain.UserLogin;

public class AuthResponse {
     private String status;

    public String getStatus() {
        return status;
    }

    public AuthResponse(String status, UserLogin userLogin) {
        this.status = status;
        this.userLogin = userLogin;
    }




    public void setStatus(String status) {
        this.status = status;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    private UserLogin userLogin;


}
