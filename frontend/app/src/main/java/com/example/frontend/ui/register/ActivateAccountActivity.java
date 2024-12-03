package com.example.frontend.ui.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.R;
import com.example.frontend.data.models.auth.ApiResponse;
import com.example.frontend.databinding.ActivityActivateAccountBinding;
import com.example.frontend.ui.LoadingDialogFragment;
import com.example.frontend.ui.login.LoginActivity;

import java.util.Objects;

public class ActivateAccountActivity extends AppCompatActivity {

    private static final int ACTIVATION_CODE_LENGTH = 8;
    private static final String LOADING_DIALOG_TAG = "loading";
    
    private ActivityActivateAccountBinding binding;
    private ActivateAccountViewModel viewModel;
    private LoadingDialogFragment loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        setupViewModel();
        setupClickListeners();
    }

    private void initializeViews() {
        EdgeToEdge.enable(this);
        binding = ActivityActivateAccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();
        loadingDialog = new LoadingDialogFragment();
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(ActivateAccountViewModel.class);
        observeViewModel();
    }

    private void setupClickListeners() {
        binding.btnActivate.setOnClickListener(v -> handleActivation());
    }

    private void handleActivation() {
        if (validateInputs()) {
            loadingDialog.show(getSupportFragmentManager(), LOADING_DIALOG_TAG);
            String activationCode = getActivationCode();
            viewModel.activateAccount(activationCode);
        }
    }

    private String getActivationCode() {
        return Objects.requireNonNull(binding.editTextActivationCode.getText())
                .toString()
                .trim();
    }

    private void observeViewModel() {
        viewModel.getApiResponse().observe(this, this::handleApiResponse);
        viewModel.getErrorMessage().observe(this, this::handleError);
    }

    private void handleApiResponse(ApiResponse response) {
        loadingDialog.dismiss();
        if (response != null && response.getStatus().equals("success")) {
            showSuccessDialog(response.getMessage());
        }
    }

    private void handleError(String error) {
        loadingDialog.dismiss();
        if (error != null) {
            showErrorDialog(error);
        }
    }

    private void showSuccessDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("¡Éxito!")
                .setMessage(message)
                .setIcon(R.drawable.ic_check_16)
                .setPositiveButton("Ingresar", (dialog, which) -> navigateToLogin())
                .show();
    }

    private void showErrorDialog(String error) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(error)
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Ok", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private boolean validateInputs() {
        String activationCode = getActivationCode();

        if (TextUtils.isEmpty(activationCode)) {
            showActivationCodeError("El código no puede estar vacío");
            return false;
        }
        
        if (activationCode.length() != ACTIVATION_CODE_LENGTH) {
            showActivationCodeError("El código es de 8 caracteres");
            return false;
        }

        return true;
    }

    private void showActivationCodeError(String errorMessage) {
        binding.editTextActivationCode.setError(errorMessage);
        binding.editTextActivationCode.requestFocus();
    }

    public void goBack(View view) {
        finish();
    }

    private void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}