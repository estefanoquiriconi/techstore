package com.example.frontend.ui.recover;

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
import com.example.frontend.databinding.ActivityRecoverPasswordBinding;

public class RecoverPasswordActivity extends AppCompatActivity {

    ActivityRecoverPasswordBinding binding;
    RecoverPasswordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRecoverPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        viewModel = new ViewModelProvider(this).get(RecoverPasswordViewModel.class);
        observerViewModel();

        binding.btnSend.setOnClickListener(v -> {
            if(validateInputs()){
                String email = binding.editTextEmail.getText().toString().trim();
                viewModel.recoverPassword(email);
            }
        });
    }

    private void observerViewModel(){
        viewModel.getApiResponse().observe(this, response -> {
            if(response != null && response.getStatus().equals("success")){
                startActivity(new Intent(this, VerificationCodeActivity.class));
                finish();
            }
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

    public void goBack(View view){
        finish();
    }

    private boolean validateInputs() {
        String email = binding.editTextEmail.getText().toString().trim();

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

        return true;
    }

    public void setWindowInsets(){
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}