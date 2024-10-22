package com.example.frontend.ui.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.R;
import com.example.frontend.databinding.ActivityActivateAccountBinding;
import com.example.frontend.ui.login.LoginActivity;

public class ActivateAccountActivity extends AppCompatActivity {

    ActivityActivateAccountBinding binding;
    ActivateAccountViewModel activateAccountViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityActivateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        activateAccountViewModel = new ViewModelProvider(this).get(ActivateAccountViewModel.class);
        observerViewModel();

        binding.btnActivate.setOnClickListener(v -> {
            if(validateInputs()){
                String activationCode = binding.editTextActivationCode.getText().toString().trim();
                activateAccountViewModel.activeAccount(activationCode);
            }
        });

    }

    public void observerViewModel() {
        activateAccountViewModel.getApiResponse().observe(this, response -> {
            if(response != null && response.getStatus().equals("success")){
                new AlertDialog.Builder(this)
                        .setTitle("OK")
                        .setMessage(response.getMessage())
                        .setIcon(R.drawable.ic_check_16)
                        .setPositiveButton("Ingresar", (dialog, which) -> {
                            startActivity(new Intent(this, LoginActivity.class));
                            finish();
                        })
                        .show();
            }
        });

        activateAccountViewModel.getErrorMessage().observe(this, error -> {
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
        String activationCode = binding.editTextActivationCode.getText().toString().trim();

        if (TextUtils.isEmpty(activationCode)) {
            binding.editTextActivationCode.setError("El código no puede estar vacío");
            binding.editTextActivationCode.requestFocus();
            return false;
        }
        if (activationCode.length() != 8) {
            binding.editTextActivationCode.setError("El código es de 8 caracteres");
            binding.editTextActivationCode.requestFocus();
            return false;
        }

        return true;
    }

    public void goBack(View view){
        finish();
    }

    public void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}