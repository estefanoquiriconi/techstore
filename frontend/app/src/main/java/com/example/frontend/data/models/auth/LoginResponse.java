package com.example.frontend.data.models.auth;

public class LoginResponse {

    private String status;
    private String message;
    private int user_id;
    private String token;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getToken() {
        return token;
    }

}
