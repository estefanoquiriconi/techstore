package com.example.frontend.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.Product;
import com.example.frontend.data.models.Review;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailViewModel extends ViewModel {

    private final ApiService apiService;
    private final MutableLiveData<Product> product;
    private final MutableLiveData<List<Review>> reviews;
    private final MutableLiveData<String> error;

    public ProductDetailViewModel() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
        product = new MutableLiveData<>();
        reviews = new MutableLiveData<>();
        error = new MutableLiveData<>();
    }

    public LiveData<Product> getProduct() {
        return product;
    }

    public LiveData<List<Review>> getReviews() {
        return reviews;
    }

    public LiveData<String> getError() {
        return error;
    }

    public void loadProductDetail(int productId) {
        apiService.getProduct(productId).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.isSuccessful() && response.body() != null) {
                    product.setValue(response.body());
                    loadProductReviews(productId);
                } else {
                    error.setValue("Error al cargar el producto");
                }
            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                error.setValue("Error de conexión");
            }
        });
    }

    private void loadProductReviews(int productId) {
        apiService.getProductReviews(productId).enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(@NonNull Call<List<Review>> call, @NonNull Response<List<Review>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    reviews.setValue(response.body());
                } else {
                    error.setValue("Error al cargar las reseñas");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Review>> call, @NonNull Throwable t) {
                error.setValue("Error al cargar las reseñas");
            }
        });
    }


}