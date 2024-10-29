package com.example.frontend.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.frontend.R;

import java.text.NumberFormat;
import java.util.Locale;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);

        ImageView ivProductImage = findViewById(R.id.ivProductImage);
        TextView tvProductName = findViewById(R.id.tvProductName);
        TextView tvProductPrice = findViewById(R.id.tvProductPrice);
        TextView tvProductDescription = findViewById(R.id.tvProductDescription);
        TextView tvProductStock = findViewById(R.id.tvProductStock);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String price = intent.getStringExtra("price");
        String description = intent.getStringExtra("description");
        String imageUrl = intent.getStringExtra("image_url");
        int stock = intent.getIntExtra("stock", 0);
        String stockStr = "Stock disponible: " + stock;

        tvProductName.setText(name);
        tvProductDescription.setText(description);
        tvProductStock.setText(stockStr);

        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "AR"));
        if(price != null) tvProductPrice.setText(formatter.format(Double.parseDouble(price)));

        Glide.with(this)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(ivProductImage);
    }
}