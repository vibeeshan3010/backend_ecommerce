package com.example.demo.response;

import com.example.demo.domain.User;

public class DeleteUserResponse {

    private String status;
    private User user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DeleteUserResponse(String status, User user) {
        this.status = status;
        this.user = user;
    }
}