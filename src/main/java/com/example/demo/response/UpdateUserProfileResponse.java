package com.example.demo.response;

import com.example.demo.domain.User;

public class UpdateUserProfileResponse {
    private User user;

    private String successmessage;

    private String failuremessage;

    public User getUser() {
        return user;
    }

    public UpdateUserProfileResponse(User user, String successmessage, String failuremessage) {
        this.user = user;
        this.successmessage = successmessage;
        this.failuremessage = failuremessage;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getsuccessmessage() {
        return successmessage;
    }

    public void setsuccessmessage(String successmessage) {
        this.successmessage = successmessage;
    }

    public String getfailuremessage() {
        return failuremessage;
    }

    public void setfailuremessage(String failuremessage) {
        this.failuremessage = failuremessage;
    }
}
