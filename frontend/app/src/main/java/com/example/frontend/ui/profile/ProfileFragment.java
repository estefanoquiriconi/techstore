package com.example.frontend.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;
    private SupportMapFragment mapFragment;
    private static final float MAP_ZOOM_LEVEL = 15f;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        loadUserData();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        initializeMapFragment();
        setupObservers();
        setupClickListeners();

        return binding.getRoot();
    }

    private void loadUserData() {
        int userId = requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
                .getInt("user_id", 1);
        profileViewModel.loadUser(userId);
    }

    private void initializeMapFragment() {
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapProfile);
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            getChildFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mapProfile, mapFragment)
                    .commit();
        }
    }

    private void setupObservers() {
        profileViewModel.getUser().observe(getViewLifecycleOwner(), this::updateUI);
    }

    private void setupClickListeners() {
        binding.floatingActionButtonLogout.setOnClickListener(v -> handleLogout());
    }

    private void updateUI(User user) {
        if (user == null) return;

        binding.fullName.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
        binding.email.setText(user.getEmail());
        binding.phone.setText(user.getPhone());
        binding.address.setText(user.getAddress());

        loadProfileImage(user.getAvatar());
        if(user.getLongitude() != 0 && user.getLatitude() != 0){
            updateMap(new LatLng(user.getLatitude(), user.getLongitude()));
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

    private void updateMap(LatLng location) {
        if (mapFragment != null) {
            mapFragment.getMapAsync(googleMap -> {
                googleMap.clear();
                googleMap.addMarker(new MarkerOptions()
                        .position(location)
                        .title("Tu ubicación"));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, MAP_ZOOM_LEVEL));
            });
        }
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
        SharedPreferences.Editor editor = requireActivity()
                .getSharedPreferences("userPreferences", Context.MODE_PRIVATE)
                .edit();
        editor.remove("token")
                .remove("user_id")
                .apply();

        startActivity(new Intent(requireActivity(), LoginActivity.class));
        requireActivity().finish();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}