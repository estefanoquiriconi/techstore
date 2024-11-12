package com.example.frontend.ui.cart;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.frontend.data.models.Product;
import com.example.frontend.data.repositories.CartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {
    private final CartRepository repository;
    private final MutableLiveData<List<CartItem>> cartItems = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<Double> totalPrice = new MutableLiveData<>();

    public static class CartItem {
        public Product product;
        public int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }
    }

    public CartViewModel(CartRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<CartItem>> getCartItems() {
        return cartItems;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Double> getTotalPrice() {
        return totalPrice;
    }

    public void loadCartProducts() {
        isLoading.setValue(true);
        Map<Integer, Integer> productQuantities = repository.getCartProductsWithQuantities();

        if (productQuantities.isEmpty()) {
            cartItems.setValue(new ArrayList<>());
            totalPrice.setValue(0.0);
            isLoading.setValue(false);
            return;
        }

        List<CartItem> items = new ArrayList<>();
        AtomicInteger pendingRequests = new AtomicInteger(productQuantities.size());

        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            int productId = entry.getKey();
            int quantity = entry.getValue();

            repository.getApiService().getProduct(productId)
                    .enqueue(new Callback<Product>() {
                        @Override
                        public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                items.add(new CartItem(response.body(), quantity));
                            }

                            if (pendingRequests.decrementAndGet() == 0) {
                                cartItems.setValue(items);
                                calculateTotal(items);
                                isLoading.setValue(false);
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                            //TODO
                        }
                    });
        }
    }

    private void calculateTotal(List<CartItem> items) {
        double total = 0;
        for (CartItem item : items) {
            total += Double.parseDouble(item.product.getPrice()) * item.quantity;
        }
        totalPrice.setValue(total);
    }

    public void updateQuantity(int productId, int newQuantity) {
        repository.updateQuantity(productId, newQuantity);
        loadCartProducts();
    }

    public void removeProduct(int productId) {
        repository.removeFromCart(productId);
        loadCartProducts();
    }

}
