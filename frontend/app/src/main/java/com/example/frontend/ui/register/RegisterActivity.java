package com.example.frontend.ui.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.MainActivity;
import com.example.frontend.R;
import com.example.frontend.databinding.ActivityRegisterBinding;
import com.example.frontend.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        observerViewModel();

        binding.tvSignIn.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
        });

        binding.btnRegister.setOnClickListener(v -> {
            if(validateInputs()){
                String lastName = binding.editTextLastName.getText().toString().trim();
                String firstName = binding.editTextFirstName.getText().toString().trim();
                String email = binding.editTextEmail.getText().toString().trim();
                String password = binding.editTextPassword.getText().toString().trim();
                registerViewModel.register(lastName, firstName, email, password);
            }
        });

    }

    public void observerViewModel() {
        registerViewModel.getApiResponse().observe(this, response -> {
            if (response != null) {
                //TODO
            }
        });

        registerViewModel.getErrorMessage().observe(this, error -> {
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
        String lastName = binding.editTextLastName.getText().toString().trim();
        String firstName = binding.editTextFirstName.getText().toString().trim();
        String email = binding.editTextEmail.getText().toString().trim();
        String password = binding.editTextPassword.getText().toString();
        String confirmPassword = binding.editTextConfirmPassword.getText().toString();

        if (TextUtils.isEmpty(lastName)) {
            binding.editTextLastName.setError("El apellido no puede estar vacío");
            binding.editTextLastName.requestFocus();
            return false;
        }
        if (lastName.length() < 3) {
            binding.editTextLastName.setError("El apellido debe tener al menos 3 letras");
            binding.editTextLastName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(firstName)) {
            binding.editTextFirstName.setError("El nombre no puede estar vacío");
            binding.editTextFirstName.requestFocus();
            return false;
        }
        if (firstName.length() < 3) {
            binding.editTextFirstName.setError("El nombre debe tener al menos 3 letras");
            binding.editTextFirstName.requestFocus();
            return false;
        }

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

        if (!password.equals(confirmPassword)) {
            binding.editTextConfirmPassword.setError("Las contraseñas no coinciden");
            binding.editTextConfirmPassword.requestFocus();
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