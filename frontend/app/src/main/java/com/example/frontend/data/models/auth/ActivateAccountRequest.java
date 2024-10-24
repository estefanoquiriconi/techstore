package com.example.frontend.data.models.auth;

public class ActivateAccountRequest {

    private String registration_code;

    public ActivateAccountRequest(String registration_code) {
        this.registration_code = registration_code;
    }

    public String getRegistration_code() {
        return registration_code;
    }

    public void setRegistration_code(String registration_code) {
        this.registration_code = registration_code;
    }
}
