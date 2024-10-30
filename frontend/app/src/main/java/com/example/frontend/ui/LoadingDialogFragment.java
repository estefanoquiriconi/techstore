package com.example.frontend.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class LoadingDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setPadding(10, 30, 10 ,30);
        progressBar.setIndeterminate(true);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(progressBar);
        builder.setCancelable(false);
        return builder.create();
    }
}
