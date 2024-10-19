package com.example.frontend.data.services;

import com.example.frontend.data.models.LoginRequest;
import com.example.frontend.data.models.ApiResponse;
import com.example.frontend.data.models.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/auth/login")
    Call<ApiResponse> login(@Body LoginRequest loginRequest);

    @POST("api/auth/register")
    Call<ApiResponse> register(@Body RegisterRequest registerRequest);
}
