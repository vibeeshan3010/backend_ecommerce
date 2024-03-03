package com.example.demo.response;

import com.example.demo.domain.Admin;


public class AdminRegistrationResponse {

    private String status;
    private Admin admin;

    public AdminRegistrationResponse(String status, Admin admin) {
        this.status = status;
        this.admin = admin;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }
}
