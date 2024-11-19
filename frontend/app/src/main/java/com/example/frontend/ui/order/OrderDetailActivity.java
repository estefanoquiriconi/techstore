package com.example.frontend.ui.order;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.frontend.R;
import com.example.frontend.data.models.Order;
import com.example.frontend.databinding.ActivityOrderDetailBinding;
import com.example.frontend.utils.CurrencyUtils;

public class OrderDetailActivity extends AppCompatActivity {

    private OrderDetailAdapter adapter;
    private ActivityOrderDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        OrderDetailViewModel viewModel = new ViewModelProvider(this).get(OrderDetailViewModel.class);
        binding.recyclerViewOrderDetail.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OrderDetailAdapter();
        binding.recyclerViewOrderDetail.setAdapter(adapter);

        int orderId = getIntent().getIntExtra("order_id", -1);
        if (orderId != -1) {
            viewModel.loadOrderDetail(orderId);
        }

        viewModel.getOrderDetail().observe(this, order -> {
            if (order != null) {
                updateUI(order);
            }
        });
    }

    private void updateUI(Order order) {
        String orderStr = "Orden #".concat(String.valueOf(order.getId()));
        binding.tvOrderId.setText(orderStr);
        binding.tvTotalAmount.setText(String.format("Total: %s", CurrencyUtils.formatToARCurrency(order.getTotalAmount())));
        adapter.setOrderDetails(order.getOrderDetails());
    }
}