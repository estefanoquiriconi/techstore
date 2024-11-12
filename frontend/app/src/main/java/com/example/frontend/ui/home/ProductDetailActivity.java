package com.example.frontend.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.frontend.data.models.Product;
import com.example.frontend.data.models.Review;
import com.example.frontend.data.repositories.CartRepository;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.databinding.ActivityProductDetailBinding;
import com.example.frontend.utils.RetrofitClient;

import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    private ProductDetailViewModel viewModel;
    private ActivityProductDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityProductDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int productId = getIntent().getIntExtra("product_id", -1);
        if (productId == -1) {
            finish();
            return;
        }

        viewModel = new ViewModelProvider(this).get(ProductDetailViewModel.class);
        setupObservers();
        viewModel.loadProductDetail(productId);

        CartRepository repository = new CartRepository(this, RetrofitClient.getClient().create(ApiService.class));
        binding.btnAddToCart.setOnClickListener(v -> {
            repository.addToCart(productId);
            new AlertDialog.Builder(this)
                    .setTitle("¡Listo!")
                    .setMessage("Producto agregado al carrito.")
                    .setPositiveButton("Entendido", (dialog, which) -> {
                        dialog.dismiss();
                    })
                    .show();
        });
    }

    private void setupObservers() {
        viewModel.getProduct().observe(this, this::updateProductUI);
        viewModel.getReviews().observe(this, this::updateReviewsUI);
        viewModel.getError().observe(this, this::showError);
    }

    private void updateProductUI(Product product) {
        binding.tvName.setText(product.getName());
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        binding.tvPrice.setText(formatter.format(Double.parseDouble(product.getPrice())));
        binding.tvDescription.setText(product.getDescription());
        binding.tvStock.setText(MessageFormat.format("Stock: {0}", product.getStock()));

        Glide.with(this)
                .load(product.getImageUrl())
                .into(binding.ivProduct);
    }

    private void updateReviewsUI(List<Review> reviews) {
        if (reviews.isEmpty()) {
            binding.tvNoReviews.setVisibility(View.VISIBLE);
            binding.recyclerReviews.setVisibility(View.GONE);
        } else {
            binding.tvNoReviews.setVisibility(View.GONE);
            binding.recyclerReviews.setVisibility(View.VISIBLE);

            ReviewsAdapter adapter = new ReviewsAdapter(reviews);
            binding.recyclerReviews.setAdapter(adapter);

            float averageRating = (float) reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);

            binding.tvAverageRating.setText(String.format(Locale.getDefault(), "%.1f", averageRating));
            binding.ratingBarAverage.setRating(averageRating);
        }
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}