package com.example.frontend.ui.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.auth.ApiResponse;
import com.example.frontend.data.models.auth.RegisterRequest;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {

    private static final int HTTP_CONFLICT = 409;
    private static final String EMAIL_ALREADY_REGISTERED = "El email ya está registrado.";
    private static final String INVALID_DATA = "Por favor, verificá los datos ingresados.";
    private static final String CONNECTION_ERROR = "Error de conexión: ";

    private final AuthService authService;
    private final MutableLiveData<ApiResponse> _apiResponse = new MutableLiveData<>();
    private final MutableLiveData<String> _errorMessage = new MutableLiveData<>();

    public RegisterViewModel() {
        authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public LiveData<ApiResponse> getApiResponse() {
        return _apiResponse;
    }

    public LiveData<String> getErrorMessage() {
        return _errorMessage;
    }

    public void register(String lastName, String firstName, String email, String password) {
        RegisterRequest request = new RegisterRequest(lastName, firstName, email, password);
        authService.register(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call,
                                   @NonNull Response<ApiResponse> response) {
                handleRegisterResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                _errorMessage.postValue(CONNECTION_ERROR + t.getMessage());
            }
        });
    }

    private void handleRegisterResponse(Response<ApiResponse> response) {
        if (response.isSuccessful() && response.body() != null) {
            _apiResponse.postValue(response.body());
        } else if (response.code() == HTTP_CONFLICT) {
            _errorMessage.postValue(EMAIL_ALREADY_REGISTERED);
        } else {
            _errorMessage.postValue(INVALID_DATA);
        }
    }
}
