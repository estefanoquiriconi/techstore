package com.example.frontend.ui.order;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.Order;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderViewModel extends ViewModel {
    private final MutableLiveData<List<Order>> orders;
    private final ApiService apiService;

    public OrderViewModel() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
        orders = new MutableLiveData<>();
    }

    public LiveData<List<Order>> getOrders() {
        return orders;
    }

    public void loadOrders(int userId) {
        apiService.getUserOrders(userId).enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(@NonNull Call<List<Order>> call, @NonNull Response<List<Order>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orders.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Order>> call, @NonNull Throwable t) {
                //TODO
            }
        });
    }


}