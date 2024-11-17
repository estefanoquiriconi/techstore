package com.example.frontend.ui.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frontend.data.repositories.CartRepository;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.databinding.FragmentCartBinding;
import com.example.frontend.utils.CurrencyUtils;
import com.example.frontend.utils.RetrofitClient;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.CartItemListener {

    private FragmentCartBinding binding;
    private CartViewModel viewModel;
    private CartAdapter adapter;
    private double total;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        initializeComponents();
        return binding.getRoot();
    }

    private void initializeComponents() {
        setupRecyclerView();
        setupViewModel();
        setupListeners();
        observeViewModelChanges();
    }

    private void setupRecyclerView() {
        adapter = new CartAdapter(this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        CartRepository repository = createCartRepository();
        ViewModelProvider.Factory factory = createViewModelFactory(repository);
        viewModel = new ViewModelProvider(this, factory).get(CartViewModel.class);
        viewModel.loadCartProducts();
    }

    private CartRepository createCartRepository() {
        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
        return new CartRepository(requireContext(), apiService);
    }

    private ViewModelProvider.Factory createViewModelFactory(CartRepository repository) {
        return new ViewModelProvider.Factory() {
            @NonNull
            @Override
            @SuppressWarnings("unchecked")
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                return (T) new CartViewModel(repository);
            }
        };
    }

    private void observeViewModelChanges() {
        viewModel.getCartItems().observe(getViewLifecycleOwner(), this::updateUI);
        viewModel.getIsLoading().observe(getViewLifecycleOwner(), this::updateLoadingState);
        viewModel.getTotalPrice().observe(getViewLifecycleOwner(), this::updateTotalPrice);
    }

    private void updateLoadingState(boolean isLoading) {
        binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void updateTotalPrice(Double totalPrice) {
        binding.tvTotal.setText(CurrencyUtils.formatToARCurrency(totalPrice));
        this.total = totalPrice;
    }

    private void setupListeners() {
        binding.btnCheckout.setOnClickListener(v -> proceedToCheckout());
    }

    private void updateUI(List<CartViewModel.CartItem> items) {
        boolean isEmpty = items.isEmpty();
        binding.recyclerView.setVisibility(isEmpty ? View.GONE : View.VISIBLE);
        binding.emptyCartLayout.setVisibility(isEmpty ? View.VISIBLE : View.GONE);
        binding.btnCheckout.setEnabled(!isEmpty);

        if (!isEmpty) {
            adapter.submitList(items);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        viewModel.loadCartProducts();
    }

    @Override
    public void onQuantityChanged(int productId, int newQuantity) {
        viewModel.updateQuantity(productId, newQuantity);
    }

    @Override
    public void onRemoveItem(int productId) {
        viewModel.removeProduct(productId);
    }

    private void proceedToCheckout() {
        Intent intent = new Intent(requireContext(), CheckoutActivity.class);
        intent.putExtra("total", String.valueOf(total));
        startActivity(intent);
    }
}