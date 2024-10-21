package com.example.frontend.ui.register;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.ActivateAccountRequest;
import com.example.frontend.data.models.ApiResponse;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivateAccountViewModel extends ViewModel {

    private final AuthService authService;
    private final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();


    public ActivateAccountViewModel() {
        this.authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void activeAccount(String activationCode) {
        ActivateAccountRequest activateAccountRequest = new ActivateAccountRequest(activationCode);

        authService.activateAccount(activateAccountRequest).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.postValue(response.body());
                } else {
                    errorMessage.postValue("No se pudo activar la cuenta.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorMessage.postValue("Error de conexión: " + t.getMessage());
            }
        });

    }
}
