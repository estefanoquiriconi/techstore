package com.example.frontend.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.MainActivity;
import com.example.frontend.R;
import com.example.frontend.databinding.ActivityLoginBinding;
import com.example.frontend.ui.register.RegisterActivity;
import com.example.frontend.utils.InputValidator;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observerViewModel();

        binding.btnLogin.setOnClickListener(v -> {
            if (InputValidator.isEmpty(binding.editTextEmail, "Por favor, ingresa tu email"))
                return;
            if (InputValidator.isInvalidEmail(binding.editTextEmail, "Verifica tu email, algo no está bien \uD83D\uDE05"))
                return;
            if (InputValidator.isEmpty(binding.editTextPassword, "¡Ups! Falta la contraseña \uD83D\uDD12"))
                return;

            String email = binding.editTextEmail.getText().toString().trim();
            String password = binding.editTextPassword.getText().toString().trim();
            loginViewModel.login(email, password);
        });

        binding.tvForgotPassword.setOnClickListener(v -> {
            //TODO
        });

        binding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }

    public void observerViewModel() {
        loginViewModel.getApiResponse().observe(this, response -> {
            if (response != null) {
                saveToken(response.getToken());
                startActivity(new Intent(this, MainActivity.class));
            }
        });

        loginViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("Por favor, verificá los datos ingresados.")
                        .setIcon(R.drawable.ic_warning)
                        .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                        .show();
            }
        });
    }

    public void saveToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("user_tokens", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("auth_token", token);
        editor.apply();
    }

    public void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}