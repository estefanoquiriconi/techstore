package com.example.frontend.ui.order;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.Order;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderDetailViewModel extends ViewModel {
    private final MutableLiveData<Order> orderLiveData = new MutableLiveData<>();
    private final ApiService apiService;

    public OrderDetailViewModel() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    public LiveData<Order> getOrderDetail() {
        return orderLiveData;
    }

    public void loadOrderDetail(int orderId) {
        apiService.getOrderDetail(orderId).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                if (response.isSuccessful() && response.body() != null) {
                    orderLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                //TODO
            }
        });
    }
}