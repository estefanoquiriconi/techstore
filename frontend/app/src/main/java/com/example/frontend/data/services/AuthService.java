package com.example.frontend.data.services;

import com.example.frontend.data.models.auth.ActivateAccountRequest;
import com.example.frontend.data.models.auth.LoginRequest;
import com.example.frontend.data.models.auth.ApiResponse;
import com.example.frontend.data.models.auth.LoginResponse;
import com.example.frontend.data.models.auth.RecoverPasswordRequest;
import com.example.frontend.data.models.auth.RegisterRequest;
import com.example.frontend.data.models.auth.ResetPasswordRequest;
import com.example.frontend.data.models.auth.VerifyCodeRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @POST("api/auth/register")
    Call<ApiResponse> register(@Body RegisterRequest registerRequest);

    @POST("api/auth/activate")
    Call<ApiResponse> activateAccount(@Body ActivateAccountRequest activateRequest);

    @POST("api/auth/recover-password")
    Call<ApiResponse> recoverPassword(@Body RecoverPasswordRequest recoverPasswordRequest);

    @POST("api/auth/verify-recover-code")
    Call<ApiResponse> verifyCode(@Body VerifyCodeRequest verifyCodeRequest);

    @POST("api/auth/reset-password")
    Call<ApiResponse> resetPassword(@Body ResetPasswordRequest requestPasswordRequest);
}
