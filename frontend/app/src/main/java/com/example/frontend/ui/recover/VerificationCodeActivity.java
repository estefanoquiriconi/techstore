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
import com.example.frontend.databinding.ActivityVerificationCodeBinding;
import com.example.frontend.ui.LoadingDialogFragment;

public class VerificationCodeActivity extends AppCompatActivity {

    ActivityVerificationCodeBinding binding;
    VerificationCodeViewModel viewModel;
    private String email;
    private LoadingDialogFragment loadingDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityVerificationCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();

        Intent intent = getIntent();
        if (intent != null) {
            this.email = intent.getStringExtra("email");
        }

        viewModel = new ViewModelProvider(this).get(VerificationCodeViewModel.class);
        observerViewModel();

        loadingDialogFragment = new LoadingDialogFragment();

        binding.btnVerify.setOnClickListener(v -> {
            if(validateInputs()){
                loadingDialogFragment.show(getSupportFragmentManager(), "loading");

                String code = binding.editTextVerificationCode.getText().toString().trim();
                viewModel.verifyCode(code);
            }
        });

        binding.ivBack.setOnClickListener(v -> {
            finish();
        });

    }

    public void observerViewModel() {
        viewModel.getApiResponse().observe(this, response -> {
            loadingDialogFragment.dismiss();
            if (response != null && response.getStatus().equals("success")) {
                Intent intent = new Intent(this, NewPasswordActivity.class);
                intent.putExtra("email", this.email);
                startActivity(intent);
                finish();
            }
        });

        viewModel.getErrorMessage().observe(this, error -> {
            loadingDialogFragment.dismiss();
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
        String code = binding.editTextVerificationCode.getText().toString().trim();

        if (TextUtils.isEmpty(code)) {
            binding.editTextVerificationCode.setError("El código no puede estar vacío");
            binding.editTextVerificationCode.requestFocus();
            return false;
        }
        if (code.length() != 8) {
            binding.editTextVerificationCode.setError("El código es de 8 caracteres");
            binding.editTextVerificationCode.requestFocus();
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