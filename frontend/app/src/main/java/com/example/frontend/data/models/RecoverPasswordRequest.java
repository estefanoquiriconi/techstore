package com.example.frontend.data.models;

public class RecoverPasswordRequest {
    private String email;

    public RecoverPasswordRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
