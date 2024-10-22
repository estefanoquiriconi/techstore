package com.example.frontend.data.models;

public class VerifyCodeRequest {

    private String recoverpass_code;

    public VerifyCodeRequest(String recoverpass_code){
        this.recoverpass_code = recoverpass_code;
    }

    public String getRecoverpass_code() {
        return recoverpass_code;
    }

    public void setRecoverpass_code(String recoverpass_code) {
        this.recoverpass_code = recoverpass_code;
    }
}
