package com.example.frontend.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.LoginRequest;
import com.example.frontend.data.models.LoginResponse;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    private final AuthService authService;
    private final MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<String>();

    public LoginViewModel() {
        authService = RetrofitClient.getClient()
                .create(AuthService.class);
    }

    public LiveData<LoginResponse> getLoginResponse() {
        return loginResponse;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void login(String email, String password) {
        LoginRequest request = new LoginRequest(email, password);

        authService.login(request).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    loginResponse.postValue(response.body());
                } else {
                    errorMessage.postValue("Por favor, verificá los datos ingresados.");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                errorMessage.postValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}