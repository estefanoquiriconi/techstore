package com.example.frontend.ui.cart;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.User;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutViewModel extends ViewModel {

    private final ApiService apiService;
    private final MutableLiveData<User> user;

    public CheckoutViewModel() {
        this.user = new MutableLiveData<>();
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void loadUser(int id) {
        apiService.getUserById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    user.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                //TODO
            }
        });
    }
}
