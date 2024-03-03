package com.example.demo.response;

public class AdminAuthResponse {
    private String status;

    public String getStatus() {
        return status;
    }
    private String redirectURL;

    public AdminAuthResponse(String status,String redirectURL) {
        this.status = status;
        this.redirectURL=redirectURL;

    }




    public void setStatus(String status) {
        this.status = status;
    }




    public String getRedirectURL() {
        return redirectURL;
    }

    public void setRedirectURL(String redirectURL) {
        this.redirectURL = redirectURL;
    }
}
