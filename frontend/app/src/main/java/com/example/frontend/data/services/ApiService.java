package com.example.frontend.data.services;

import com.example.frontend.data.models.Banner;
import com.example.frontend.data.models.Category;
import com.example.frontend.data.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("api/categories")
    Call<List<Category>> getCategories();

    @GET("api/banners")
    Call<List<Banner>> getBanners();

    @GET("api/products")
    Call<List<Product>> getProducts();

}
