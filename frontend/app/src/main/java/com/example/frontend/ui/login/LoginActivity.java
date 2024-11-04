package com.example.frontend.ui.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.HomeActivity;
import com.example.frontend.R;
import com.example.frontend.databinding.ActivityLoginBinding;
import com.example.frontend.ui.LoadingDialogFragment;
import com.example.frontend.ui.recover.RecoverPasswordActivity;
import com.example.frontend.ui.register.ActivateAccountActivity;
import com.example.frontend.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel loginViewModel;
    private LoadingDialogFragment loadingDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        observerViewModel();

        loadingDialogFragment = new LoadingDialogFragment();

        binding.btnLogin.setOnClickListener(v -> {
            if(validateInputs()){
                loadingDialogFragment.show(getSupportFragmentManager(), "loading");

                String email = binding.editTextEmail.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                loginViewModel.login(email, password);
            }

        });

        binding.tvForgotPassword.setOnClickListener(v -> {
            startActivity(new Intent(this, RecoverPasswordActivity.class));
        });

        binding.tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

    }

    public void observerViewModel() {
        loginViewModel.getApiResponse().observe(this, response -> {
            loadingDialogFragment.dismiss();
            if (response != null && response.getStatus().equals("success")) {
                saveUserInfo(response.getUser_id(), response.getToken());
                startActivity(new Intent(this, HomeActivity.class));
                finish();
            }
        });

        loginViewModel.getErrorMessage().observe(this, error -> {
            loadingDialogFragment.dismiss();
            if (error != null) {
                if (error.equals("Activación pendiente")) {
                    new AlertDialog.Builder(this)
                            .setTitle("Advertencia")
                            .setMessage(error)
                            .setIcon(R.drawable.ic_warning)
                            .setPositiveButton("Activar", (dialog, which) -> {
                                startActivity(new Intent(this, ActivateAccountActivity.class));
                                finish();
                            })
                            .show();
                } else {
                    new AlertDialog.Builder(this)
                            .setTitle("Error")
                            .setMessage(error)
                            .setIcon(R.drawable.ic_warning)
                            .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                            .show();
                }
            }
        });
    }

    public void saveUserInfo(int userId, String token) {
        SharedPreferences sharedPreferences = getSharedPreferences("userPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.putInt("user_id", userId);
        editor.apply();
    }

    private boolean validateInputs() {
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            binding.editTextEmail.setError("El correo no puede estar vacío");
            binding.editTextEmail.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextEmail.setError("Ingresa un correo válido");
            binding.editTextEmail.requestFocus();
            return false;
        }

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

        return true;
    }

    public void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}