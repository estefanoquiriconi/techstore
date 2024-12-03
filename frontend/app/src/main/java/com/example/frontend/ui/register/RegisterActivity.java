package com.example.frontend.ui.register;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.R;
import com.example.frontend.data.models.auth.ApiResponse;
import com.example.frontend.databinding.ActivityRegisterBinding;
import com.example.frontend.ui.LoadingDialogFragment;
import com.example.frontend.ui.login.LoginActivity;

import java.util.Locale;
import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;
    private LoadingDialogFragment loadingDialogFragment;

    private static final int MIN_NAME_LENGTH = 3;
    private static final int MIN_PASSWORD_LENGTH = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeViews();
        setupViewModel();
        setupClickListeners();
    }

    private void initializeViews() {
        EdgeToEdge.enable(this);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setWindowInsets();
        loadingDialogFragment = new LoadingDialogFragment();
    }

    private void setupViewModel() {
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        observeViewModel();
    }

    private void setupClickListeners() {
        binding.tvSignIn.setOnClickListener(v -> navigateToLogin());
        binding.btnRegister.setOnClickListener(v -> handleRegistration());
    }

    private void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    private void handleRegistration() {
        if (validateInputs()) {
            loadingDialogFragment.show(getSupportFragmentManager(), "loading");
            submitRegistration();
        }
    }

    private void submitRegistration() {
        String lastName = getTextFromEditText(binding.editTextLastName);
        String firstName = getTextFromEditText(binding.editTextFirstName);
        String email = getTextFromEditText(binding.editTextEmail);
        String password = getTextFromEditText(binding.editTextPassword);
        registerViewModel.register(lastName, firstName, email, password);
    }

    private void observeViewModel() {
        registerViewModel.getApiResponse().observe(this, this::handleApiResponse);
        registerViewModel.getErrorMessage().observe(this, this::handleError);
    }

    private void handleApiResponse(ApiResponse response) {
        if (loadingDialogFragment != null && loadingDialogFragment.isAdded()) {
            loadingDialogFragment.dismiss();
        }
        if (response != null && response.getStatus().equals("success")) {
            startActivity(new Intent(this, ActivateAccountActivity.class));
            finish();
        }
    }

    private void handleError(String error) {
        if (loadingDialogFragment != null && loadingDialogFragment.isAdded()) {
            loadingDialogFragment.dismiss();
        }
        if (error != null) {
            showErrorDialog(error);
        }
    }

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setIcon(R.drawable.ic_warning)
                .setPositiveButton("Entendido", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private boolean validateInputs() {
        return validateName(binding.editTextLastName, "apellido")
                && validateName(binding.editTextFirstName, "nombre")
                && validateEmail()
                && validatePassword()
                && validatePasswordMatch();
    }

    private boolean validateName(EditText editText, String fieldName) {
        String text = getTextFromEditText(editText);
        if (TextUtils.isEmpty(text)) {
            showError(editText, String.format(Locale.getDefault(), "El %s no puede estar vacío", fieldName));
            return false;
        }
        if (text.length() < MIN_NAME_LENGTH) {
            showError(editText, String.format(Locale.getDefault(), "El %s debe tener al menos %d letras", fieldName, MIN_NAME_LENGTH));
            return false;
        }
        return true;
    }

    private boolean validateEmail() {
        String email = getTextFromEditText(binding.editTextEmail);
        if (TextUtils.isEmpty(email)) {
            showError(binding.editTextEmail, "El correo no puede estar vacío");
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            showError(binding.editTextEmail, "Ingresa un correo válido");
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        String password = getTextFromEditText(binding.editTextPassword);
        if (TextUtils.isEmpty(password)) {
            showError(binding.editTextPassword, "La contraseña no puede estar vacía");
            return false;
        }
        if (password.length() < MIN_PASSWORD_LENGTH) {
            showError(binding.editTextPassword, "La contraseña debe tener al menos 5 caracteres");
            return false;
        }
        return true;
    }

    private boolean validatePasswordMatch() {
        String password = getTextFromEditText(binding.editTextPassword);
        String confirmPassword = getTextFromEditText(binding.editTextConfirmPassword);
        if (!password.equals(confirmPassword)) {
            showError(binding.editTextConfirmPassword, "Las contraseñas no coinciden");
            return false;
        }
        return true;
    }

    private String getTextFromEditText(EditText editText) {
        return Objects.requireNonNull(editText.getText()).toString().trim();
    }

    private void showError(EditText editText, String message) {
        editText.setError(message);
        editText.requestFocus();
    }

    private void setWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}