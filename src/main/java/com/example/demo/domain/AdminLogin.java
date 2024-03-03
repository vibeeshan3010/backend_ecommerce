package com.example.demo.domain;

public class AdminLogin {
    private String  email;
    private String password;

    private boolean isLoggedIn;

    public static int attempts=0;

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

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

    public static int getAttempts() {
        return attempts;
    }

    public static void setAttempts(int attempts) {
        AdminLogin.attempts = attempts;
    }
}
