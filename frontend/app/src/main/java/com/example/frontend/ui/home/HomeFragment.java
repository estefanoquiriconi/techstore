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
    private ProductAdapter productAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupUI();
        setupListeners();
        observerViewModel();
        loadData();

        return binding.getRoot();
    }

    private void setupUI() {
        setupCategoryRecyclerView();
        setupBannerViewPager();
        setupProductRecyclerView();
    }

    public void setupListeners(){
        categoryAdapter.setOnItemCLickListener(category -> viewModel.getFilterProducts(category.getName(), null));

        binding.btnMinPrice.setOnClickListener(v -> {
            viewModel.getFilterProducts(viewModel.getCategoryFilter().getValue(),"asc");
        });

        binding.btnMayPrice.setOnClickListener(v -> {
            viewModel.getFilterProducts(viewModel.getCategoryFilter().getValue() ,"desc");
        });

        binding.tvSeeAll.setOnClickListener(v -> {
            viewModel.loadProducts();
        });
    }

    private void setupProductRecyclerView() {
        binding.recyclerViewProduct.setLayoutManager(new LinearLayoutManager(getContext()));
        productAdapter = new ProductAdapter(this.getContext());
        binding.recyclerViewProduct.setAdapter(productAdapter);
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

        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> productAdapter.setProducts(products));

        viewModel.getIsLoadingProducts().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarProduct.setVisibility(isLoading ? View.VISIBLE : View.GONE));
    }

    private void loadData() {
        viewModel.loadCategories();
        viewModel.loadBanners();
        viewModel.loadProducts();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}