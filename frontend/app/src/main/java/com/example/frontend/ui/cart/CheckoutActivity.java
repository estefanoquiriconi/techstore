package com.example.frontend.ui.cart;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.frontend.R;
import com.example.frontend.data.models.Order;
import com.example.frontend.data.repositories.CartRepository;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.databinding.ActivityCheckoutBinding;
import com.example.frontend.utils.CurrencyUtils;
import com.example.frontend.utils.RetrofitClient;

import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {

    private ActivityCheckoutBinding binding;
    private Random random;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.apiService = RetrofitClient.getClient().create(ApiService.class);

        String totalText = "Total a pagar: " + CurrencyUtils.formatToARCurrency(Double.parseDouble(getIntent().getStringExtra("total")));
        binding.totalAmountText.setText(totalText);

        binding.payButton.setOnClickListener(v -> {
            if (validateFields()) {
                showProcessingDialog();
                paymentRequest();
            }
        });
        random = new Random();
    }

    private void paymentRequest() {
        new Handler().postDelayed(() -> {
            boolean isSuccess = random.nextBoolean();

            ProcessingDialogFragment dialog = (ProcessingDialogFragment) getSupportFragmentManager().findFragmentByTag("ProcessingDialog");
            if (dialog != null) {
                dialog.dismiss();
            }

            showPaymentResult(isSuccess);
        }, 3000);
    }

    private boolean validateFields() {
        if (Objects.requireNonNull(binding.cardNumberInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.expiryDateInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.cvvInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.cardHolderInput.getText()).toString().isEmpty()
        ) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showPaymentResult(boolean isSuccess) {
        if (isSuccess) {
            CartRepository cartRepository = new CartRepository(this, apiService);
            cartRepository.clearCart();
            Order order = new Order();
            order.setUserId(this.getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1));
            order.setTotalAmount(Double.parseDouble(getIntent().getStringExtra("total")));
            apiService.saveOrder(order).enqueue(new Callback<Order>() {
                @Override
                public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                    new AlertDialog.Builder(CheckoutActivity.this)
                            .setTitle("¡Pago exitoso!")
                            .setMessage("Su pago ha sido procesado correctamente")
                            .setPositiveButton("Aceptar", (dialog, which) -> finish())
                            .show();
                }

                @Override
                public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                    Toast.makeText(CheckoutActivity.this, "Algo salió mal", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("¡Pago rechazado!")
                    .setMessage("Lo sentimos, su pago ha sido rechazado. Intente nuevamente")
                    .setPositiveButton("Aceptar", null)
                    .show();
        }
    }

    private void showProcessingDialog() {
        ProcessingDialogFragment processingDialog = new ProcessingDialogFragment();
        processingDialog.setCancelable(false);
        processingDialog.show(getSupportFragmentManager(), "ProcessingDialog");
    }
}

