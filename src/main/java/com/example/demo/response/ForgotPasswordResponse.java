package com.example.demo.response;

public class ForgotPasswordResponse {

    private String changePasswordSuccessMsg;
    private String changePasswordFailMsg;

    public ForgotPasswordResponse(String changePasswordSuccessMsg, String changePasswordFailMsg) {
        this.changePasswordSuccessMsg = changePasswordSuccessMsg;
        this.changePasswordFailMsg = changePasswordFailMsg;
    }

    public String getChangePasswordSuccessMsg() {
        return changePasswordSuccessMsg;
    }

    public void setChangePasswordSuccessMsg(String changePasswordSuccessMsg) {
        this.changePasswordSuccessMsg = changePasswordSuccessMsg;
    }

    public String getChangePasswordFailMsg() {
        return changePasswordFailMsg;
    }

    public void setChangePasswordFailMsg(String changePasswordFailMsg) {
        this.changePasswordFailMsg = changePasswordFailMsg;
    }
}
