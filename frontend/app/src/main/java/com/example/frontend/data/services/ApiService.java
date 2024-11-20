package com.example.frontend.data.services;

import com.example.frontend.data.models.Banner;
import com.example.frontend.data.models.Category;
import com.example.frontend.data.models.Order;
import com.example.frontend.data.models.Product;
import com.example.frontend.data.models.Review;
import com.example.frontend.data.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @GET("api/products/search/{query}")
    Call<List<Product>> getProductsByQuery(@Path("query") String query);

    @GET("api/users/{id}")
    Call<User> getUserById(@Path("id") int id);

    @GET("api/products/{id}")
    Call<Product> getProduct(@Path("id") int productId);

    @GET("api/reviews/product/{id}")
    Call<List<Review>> getProductReviews(@Path("id") int productId);

    @GET("api/orders/user/{id}")
    Call<List<Order>> getUserOrders(@Path("id") int userId);

    @POST("api/orders")
    Call<Order> saveOrder(@Body Order order);

    @POST("api/orders/details")
    Call<Order> saveOrderWithDetails(@Body Order order);

    @GET("api/orders/{id}")
    Call<Order> getOrderDetail(@Path("id") int orderId);


}
