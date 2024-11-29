package com.example.frontend.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.example.frontend.R;
import com.example.frontend.data.models.User;
import com.example.frontend.databinding.FragmentProfileBinding;
import com.example.frontend.ui.login.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private static final float MAP_ZOOM_LEVEL = 15f;
    private GoogleMap googleMap;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        binding.mapView.onCreate(savedInstanceState);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getUser().observe(getViewLifecycleOwner(), this::updateUI);
        loadUserData();
        binding.floatingActionButtonLogout.setOnClickListener(v -> handleLogout());
        return binding.getRoot();
    }

    private void loadUserData() {
        profileViewModel.loadUser(requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1));
    }

    private void updateUI(User user) {
        if (user == null) return;
        binding.fullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        binding.email.setText(user.getEmail());
        binding.phone.setText(user.getPhone());
        binding.address.setText(user.getAddress());

        loadProfileImage(user.getAvatar());

        if (user.getLongitude() != 0 && user.getLatitude() != 0) {
            LatLng userLocation = new LatLng(user.getLatitude(), user.getLongitude());
            binding.mapView.getMapAsync(map -> {
                googleMap = map;
                googleMap.addMarker(new MarkerOptions().position(userLocation).title(user.getAddress()));
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation, MAP_ZOOM_LEVEL));
            });
        }

    }

    private void loadProfileImage(String avatarUrl) {
        RequestOptions requestOptions = new RequestOptions()
                .transform(new CircleCrop())
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_error)
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(requireContext())
                .load(avatarUrl)
                .apply(requestOptions)
                .into(binding.profileImage);
    }

    private void handleLogout() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Cerrar sesión")
                .setMessage("¿Estás seguro que deseas cerrar sesión?")
                .setPositiveButton("Sí", (dialog, which) -> logout())
                .setNegativeButton("No", null)
                .show();
    }

    private void logout() {
        clearUserPreferences();
        navigateToLogin();
    }

    private void clearUserPreferences() {
        requireActivity()
                .getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
                .edit()
                .remove("token")
                .remove("user_id")
                .apply();
    }

    private void navigateToLogin() {
        startActivity(new Intent(requireActivity(), LoginActivity.class));
        requireActivity().finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        binding.mapView.onPause();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        binding.mapView.onLowMemory();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding.mapView.onDestroy();
        binding = null;
    }
}