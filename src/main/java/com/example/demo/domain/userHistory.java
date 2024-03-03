package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="user_history")
public class userHistory {

    @Id
    @Column(name="email")
    private String email;

    private boolean isLoggedIn;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public LocalDateTime getLoggedInTime() {
        return LoggedInTime;
    }

    public void setLoggedInTime(LocalDateTime loggedInTime) {
        LoggedInTime = loggedInTime;
    }

    public LocalDateTime getLoggedOutTime() {
        return LoggedOutTime;
    }

    public void setLoggedOutTime(LocalDateTime loggedOutTime) {
        LoggedOutTime = loggedOutTime;
    }

    private LocalDateTime LoggedInTime;
    private LocalDateTime LoggedOutTime;
}
