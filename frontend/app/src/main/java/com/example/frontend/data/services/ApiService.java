package com.example.frontend.data.services;

import com.example.frontend.data.models.Banner;
import com.example.frontend.data.models.Category;
import com.example.frontend.data.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("api/categories")
    Call<List<Category>> getCategories();

    @GET("api/banners")
    Call<List<Banner>> getBanners();

    @GET("api/products")
    Call<List<Product>> getProducts();

    @GET("api/products")
    Call<List<Product>> getFilterProducts(
            @Query("category") String category,
            @Query("sortBy") String sortBy,
            @Query("order") String order
    );


}
