package com.example.frontend.utils;

import android.widget.EditText;
import android.util.Patterns;

public class InputValidator {

    public static boolean isEmpty(EditText editText, String errorMessage) {
        String input = editText.getText().toString().trim();
        if (input.isEmpty()) {
            editText.setError(errorMessage);
            editText.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean isInvalidEmail(EditText editText, String errorMessage) {
        String email = editText.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editText.setError(errorMessage);
            editText.requestFocus();
            return true;
        }
        return false;
    }
}
