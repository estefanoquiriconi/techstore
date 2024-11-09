package com.example.frontend.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.frontend.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;
    private CategoryAdapter categoryAdapter;
    private BannerAdapter bannerAdapter;
    private ProductAdapter productAdapter;
    private final Handler sliderHandler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);

        setupUI();
        setupListeners();
        observerViewModel();
        loadData();

        startAutoSlide();
        return binding.getRoot();
    }

    private void setupUI() {
        setupCategoryRecyclerView();
        setupBannerViewPager();
        setupProductRecyclerView();
    }

    public void setupListeners() {
        categoryAdapter.setOnItemCLickListener(category -> viewModel.getFilterProducts(category.getName(), null));

        binding.btnMinPrice.setOnClickListener(v -> {
            viewModel.getFilterProducts(viewModel.getCategoryFilter().getValue(), "asc");
        });

        binding.btnMayPrice.setOnClickListener(v -> {
            viewModel.getFilterProducts(viewModel.getCategoryFilter().getValue(), "desc");
        });

        binding.tvSeeAllCategories.setOnClickListener(v -> {
            viewModel.loadProducts();
        });

        binding.tvSeeAllProducts.setOnClickListener(v -> {
            viewModel.loadProducts();
        });

        binding.editTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            String query = binding.editTextSearch.getText().toString().trim();
            if (!query.isEmpty()) {
                viewModel.getProductsByQuery(query);
                InputMethodManager inputMM = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMM.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return true;
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

    public void startAutoSlide() {
        sliderHandler.postDelayed(slideRunnable, 5000);
    }

    public Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            int currentItem = binding.viewPager2.getCurrentItem();
            int totalItems = bannerAdapter.getItemCount();
            if(totalItems != 0){
                binding.viewPager2.setCurrentItem((currentItem + 1) % totalItems, true);
                sliderHandler.postDelayed(this, 5000);
            }
        }
    };

    private void observerViewModel() {
        viewModel.getCategories().observe(getViewLifecycleOwner(), categories -> categoryAdapter.setCategories(categories));

        viewModel.getIsLoadingCategories().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarCategory.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getBanners().observe(getViewLifecycleOwner(), banners -> bannerAdapter.setBanners(banners));

        viewModel.getIsLoadingBanners().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarBanner.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getProducts().observe(getViewLifecycleOwner(), products -> {
            if (products.isEmpty()) {
                viewModel.loadProducts();
                new AlertDialog.Builder(requireContext())
                        .setTitle("Producto no encontrado")
                        .setMessage("No pudimos encontrar ningún producto que coincida con tu búsqueda")
                        .setPositiveButton("Entendido", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .show();
            }
            productAdapter.setProducts(products);
        });
        viewModel.getIsLoadingProducts().observe(getViewLifecycleOwner(), isLoading -> binding.progressBarProduct.setVisibility(isLoading ? View.VISIBLE : View.GONE));

        viewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            binding.tvUserName.setText(user.getFirstName());
            Glide.with(this)
                    .load(user.getAvatar())
                    .transform(new CircleCrop())
                    .into(binding.ivProfile);
        });
    }

    private void loadData() {
        viewModel.loadUser(requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1));
        viewModel.loadCategories();
        viewModel.loadBanners();
        viewModel.loadProducts();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        sliderHandler.removeCallbacks(slideRunnable);
    }
}