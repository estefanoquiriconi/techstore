package com.example.frontend.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.auth.ActivateAccountRequest;
import com.example.frontend.data.models.auth.ApiResponse;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivateAccountViewModel extends ViewModel {

    private static final String ACTIVATION_ERROR = "No se pudo activar la cuenta, por favor verifica que el código sea correcto.";
    private static final String CONNECTION_ERROR = "Error de conexión: ";

    private final AuthService authService;
    private final MutableLiveData<ApiResponse> _apiResponse = new MutableLiveData<>();
    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();

    public ActivateAccountViewModel() {
        authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public LiveData<ApiResponse> getApiResponse() {
        return _apiResponse;
    }

    public LiveData<String> getErrorMessage() {
        return _errorMessage;
    }

    public void activateAccount(String activationCode) {
        if (activationCode == null || activationCode.isEmpty()) {
            _errorMessage.postValue(ACTIVATION_ERROR);
            return;
        }

        ActivateAccountRequest request = new ActivateAccountRequest(activationCode);
        authService.activateAccount(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                handleActivationResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                _errorMessage.postValue(CONNECTION_ERROR + t.getMessage());
            }
        });
    }

    private void handleActivationResponse(Response<ApiResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            _apiResponse.postValue(response.body());
        } else {
            _errorMessage.postValue(ACTIVATION_ERROR);
        }
    }
}
