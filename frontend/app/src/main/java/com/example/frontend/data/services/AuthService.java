package com.example.frontend.data.services;

import com.example.frontend.data.models.LoginRequest;
import com.example.frontend.data.models.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);
}
