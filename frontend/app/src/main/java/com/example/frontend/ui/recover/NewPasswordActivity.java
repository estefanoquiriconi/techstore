package com.example.frontend.ui.recover;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.R;
import com.example.frontend.databinding.ActivityNewPasswordBinding;

public class NewPasswordActivity extends AppCompatActivity {

    ActivityNewPasswordBinding binding;
    NewPasswordViewModel viewModel;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityNewPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        Intent intent = getIntent();
        if (intent != null) {
            this.email = intent.getStringExtra("email");
        }

        viewModel = new ViewModelProvider(this).get(NewPasswordViewModel.class);
        observerViewModel();

        binding.btnResetPassword.setOnClickListener(v -> {
            if (validateInputs()) {
                String newPassword = binding.editTextPassword.getText().toString().trim();
                viewModel.resetPassword(email, newPassword);
            }
        });

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void observerViewModel() {
        viewModel.getApiResponse().observe(this, response -> {
            startActivity(new Intent(this, UpdatedPasswordActivity.class));
            finish();
        });

        viewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage(error)
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }

    private boolean validateInputs() {
        String password = binding.editTextPassword.getText().toString().trim();
        String confirmPassword = binding.editTextConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            binding.editTextPassword.setError("La contraseña no puede estar vacía");
            binding.editTextPassword.requestFocus();
            return false;
        }
        if (password.length() < 5) {
            binding.editTextPassword.setError("La contraseña debe tener al menos 5 caracteres");
            binding.editTextPassword.requestFocus();
            return false;
        }

        if (!password.equals(confirmPassword)) {
            binding.editTextConfirmPassword.setError("Las contraseñas no coinciden");
            binding.editTextConfirmPassword.requestFocus();
            return false;
        }

        return true;
    }

    private void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}