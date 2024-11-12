package com.example.frontend.ui.cart;

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
import com.example.frontend.utils.RetrofitClient;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class CartFragment extends Fragment implements CartAdapter.CartItemListener {

    FragmentCartBinding binding;
    private CartViewModel viewModel;
    private CartAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);

        setupRecyclerView();
        setupViewModel();
        setupListeners();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CartAdapter(this);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setupViewModel() {
        CartRepository repository = new CartRepository(requireContext(),
                RetrofitClient.getClient().create(ApiService.class));

        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.Factory() {
                    @NonNull
                    @Override
                    @SuppressWarnings("unchecked")
                    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                        return (T) new CartViewModel(repository);
                    }
                }).get(CartViewModel.class);

        viewModel.getCartItems().observe(getViewLifecycleOwner(), this::updateUI);

        viewModel.getIsLoading().observe(getViewLifecycleOwner(), isLoading -> binding.progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getTotalPrice().observe(getViewLifecycleOwner(), total -> {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
            binding.tvTotal.setText(formatter.format(total));
        });

        viewModel.loadCartProducts();
    }

    private void setupListeners() {
        binding.btnCheckout.setOnClickListener(v -> proceedToCheckout());
    }

    private void updateUI(List<CartViewModel.CartItem> items) {
        if (items.isEmpty()) {
            binding.recyclerView.setVisibility(View.GONE);
            binding.emptyCartLayout.setVisibility(View.VISIBLE);
            binding.btnCheckout.setEnabled(false);
        } else {
            binding.recyclerView.setVisibility(View.VISIBLE);
            binding.emptyCartLayout.setVisibility(View.GONE);
            binding.btnCheckout.setEnabled(true);
            adapter.submitList(items);
        }
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
        //TODO
    }

}