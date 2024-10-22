package com.example.frontend.data.models;

public class ResetPasswordRequest {

    private String email;
    private String new_password;

    public ResetPasswordRequest(String email, String new_password) {
        this.email = email;
        this.new_password = new_password;
    }

    public String getEmail() {
        return email;
    }

    public String getNew_password() {
        return new_password;
    }
}
