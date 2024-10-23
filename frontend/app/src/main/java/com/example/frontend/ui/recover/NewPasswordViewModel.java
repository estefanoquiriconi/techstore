package com.example.frontend.ui.recover;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.ApiResponse;
import com.example.frontend.data.models.ResetPasswordRequest;
import com.example.frontend.data.services.AuthService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewPasswordViewModel extends ViewModel {

    private final AuthService authService;
    private final MutableLiveData<ApiResponse> apiResponse = new MutableLiveData<>();
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public NewPasswordViewModel() {
        this.authService = RetrofitClient.getClient().create(AuthService.class);
    }

    public MutableLiveData<ApiResponse> getApiResponse() {
        return apiResponse;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void resetPassword(String email, String new_password){
        ResetPasswordRequest request = new ResetPasswordRequest(email, new_password);

        authService.resetPassword(request).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    apiResponse.postValue(response.body());
                }else {
                    errorMessage.postValue("Algo salió mal, intente nuevamente más tarde.");
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                errorMessage.postValue("Error de conexión: " + t.getMessage());
            }
        });
    }
}
