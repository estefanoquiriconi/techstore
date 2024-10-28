package com.example.frontend.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frontend.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private CategoryAdapter categoryAdapter;
    private BannerAdapter bannerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupUI();
        observerViewModel();
        loadData();

        return binding.getRoot();
    }

    private void setupUI() {
        setupCategoryRecyclerView();
        setupBannerViewPager();
    }

    private void setupCategoryRecyclerView() {
        binding.recyclerViewCategory.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this.getContext());
        binding.recyclerViewCategory.setAdapter(categoryAdapter);
    }

    private void setupBannerViewPager() {
        bannerAdapter = new BannerAdapter(this.getContext());
        binding.viewPager2.setAdapter(bannerAdapter);
    }

    private void observerViewModel() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), categories -> categoryAdapter.setCategories(categories));

        viewModel.getIsLoadingCategories().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarCategory.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getBanners().observe(getViewLifecycleOwner(), banners -> bannerAdapter.setBanners(banners));

        viewModel.getIsLoadingBanners().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarBanner.setVisibility(isLoading ? View.VISIBLE : View.GONE));
    }

    private void loadData() {
        viewModel.loadCategories();
        viewModel.loadBanners();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}