package com.example.frontend.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.Banner;
import com.example.frontend.data.models.Category;
import com.example.frontend.data.models.Product;
import com.example.frontend.data.models.User;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.utils.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final ApiService apiService;

    private final MutableLiveData<List<Category>> categories;
    private final MutableLiveData<List<Banner>> banners;
    private final MutableLiveData<List<Product>> products;
    private final MutableLiveData<Boolean> isLoadingCategories;
    private final MutableLiveData<Boolean> isLoadingBanners;
    private final MutableLiveData<Boolean> isLoadingProducts;
    private final MutableLiveData<String> categoryFilter;
    private final MutableLiveData<String> sortOrderFilter;
    private final MutableLiveData<User> user;

    public HomeViewModel() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
        categories = new MutableLiveData<>();
        banners = new MutableLiveData<>();
        products = new MutableLiveData<>();
        isLoadingCategories = new MutableLiveData<>();
        isLoadingBanners = new MutableLiveData<>();
        isLoadingProducts = new MutableLiveData<>();
        categoryFilter = new MutableLiveData<>();
        sortOrderFilter = new MutableLiveData<>();
        user = new MutableLiveData<>();
    }

    public LiveData<List<Category>> getCategories() {
        return categories;
    }

    public LiveData<List<Banner>> getBanners() {
        return banners;
    }

    public LiveData<List<Product>> getProducts() {
        return products;
    }

    public LiveData<Boolean> getIsLoadingBanners() {
        return isLoadingBanners;
    }

    public LiveData<Boolean> getIsLoadingCategories() {
        return isLoadingCategories;
    }

    public LiveData<Boolean> getIsLoadingProducts() {
        return isLoadingProducts;
    }

    public LiveData<String> getCategoryFilter() {
        return categoryFilter;
    }

    public LiveData<String> getSortOrderFilter() {
        return sortOrderFilter;
    }

    public LiveData<User> getUser() {
        return user;
    }

    public void loadCategories() {
        isLoadingCategories.setValue(true);
        apiService.getCategories().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(@NonNull Call<List<Category>> call, @NonNull Response<List<Category>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    categories.setValue(response.body());
                }
                isLoadingCategories.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Category>> call, @NonNull Throwable t) {
                isLoadingCategories.setValue(false);
            }
        });
    }

    public void loadBanners() {
        isLoadingBanners.setValue(true);
        apiService.getBanners().enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<List<Banner>> call, @NonNull Response<List<Banner>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    banners.setValue(response.body());
                }
                isLoadingBanners.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {
                isLoadingBanners.setValue(false);
            }
        });
    }

    public void loadProducts() {
        isLoadingProducts.setValue(true);
        this.categoryFilter.setValue(null);
        this.sortOrderFilter.setValue(null);
        apiService.getProducts().enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                }
                isLoadingProducts.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                isLoadingProducts.setValue(false);
            }
        });
    }

    public void getFilterProducts(String category, String order) {
        isLoadingProducts.setValue(true);
        this.categoryFilter.setValue(category);
        this.sortOrderFilter.setValue(order);
        apiService.getFilterProducts(categoryFilter.getValue(), "price", sortOrderFilter.getValue()).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                }
                isLoadingProducts.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                isLoadingProducts.setValue(false);
            }
        });
    }

    public void getProductsByQuery(String query) {
        isLoadingProducts.setValue(true);
        apiService.getProductsByQuery(query).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(@NonNull Call<List<Product>> call, @NonNull Response<List<Product>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    products.setValue(response.body());
                }
                isLoadingProducts.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<List<Product>> call, @NonNull Throwable t) {
                isLoadingProducts.setValue(false);
            }
        });
    }

    public void loadUser(int id) {
        apiService.getUserById(id).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    user.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
            }
        });
    }

}