package com.example.frontend.ui.order;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.frontend.databinding.FragmentOrderBinding;


public class OrderFragment extends Fragment {

    private FragmentOrderBinding binding;
    private OrderViewModel orderViewModel;

    private OrderAdapter orderAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        orderViewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        binding = FragmentOrderBinding.inflate(inflater, container, false);

        setupRecyclerView();
        setupViewModel();
        setupSwipeRefresh();

        return binding.getRoot();
    }

    private void setupRecyclerView() {
        orderAdapter = new OrderAdapter(this.getContext());
        binding.ordersRecyclerView.setAdapter(orderAdapter);
        binding.ordersRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setupViewModel() {
        orderViewModel.getOrders().observe(getViewLifecycleOwner(), orders -> {
            binding.progressBar.setVisibility(View.GONE);
            binding.swipeRefresh.setRefreshing(false);

            if (orders.isEmpty()) {
                binding.emptyView.setVisibility(View.VISIBLE);
                binding.ordersRecyclerView.setVisibility(View.GONE);
            } else {
                binding.emptyView.setVisibility(View.GONE);
                binding.ordersRecyclerView.setVisibility(View.VISIBLE);
                orderAdapter.setOrders(orders);
            }
        });

        loadOrders();
    }

    private void setupSwipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener(this::loadOrders);
    }

    private void loadOrders() {
        binding.progressBar.setVisibility(View.VISIBLE);
        orderViewModel.loadOrders(requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}