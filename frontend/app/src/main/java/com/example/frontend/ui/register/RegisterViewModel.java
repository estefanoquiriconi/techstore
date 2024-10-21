package com.example.frontend.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.ApiResponse;
import com.example.frontend.data.models.RegisterRequest;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private final AuthService authService;
    private final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public RegisterViewModel() {
        this.authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void register(String last_name, String first_name, String email, String password) {
        RegisterRequest request = new RegisterRequest(last_name, first_name, email, password);

        authService.register(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    apiResponse.postValue(response.body());
                } else if (response.code() == 409) {
                    errorMessage.postValue("El email ya está registrado.");
                } else {
                    errorMessage.postValue("Por favor, verificá los datos ingresados.");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                errorMessage.postValue("Error de conexión: " + t.getMessage());
            }
        });
    }

}
