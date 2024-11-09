package com.example.frontend.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.example.frontend.HomeActivity;
import com.example.frontend.R;
import com.example.frontend.databinding.FragmentProfileBinding;
import com.example.frontend.ui.login.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        setupViewModel();

        binding.floatingActionButtonLogout.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("token");
            editor.remove("user_id");
            editor.apply();

            startActivity(new Intent(requireActivity(), LoginActivity.class));
            requireActivity().finish();
        });

        profileViewModel.loadUser(requireActivity().getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1));
        return binding.getRoot();
    }

    private void setupViewModel(){
        profileViewModel.getUser().observe(getViewLifecycleOwner(), user -> {
            String fullUserName = user.getFirstName() + " " + user.getLastName();
            LatLng latLng = new LatLng(user.getLatitude(), user.getLongitude());
            binding.fullName.setText(fullUserName);
            binding.email.setText(user.getEmail());
            binding.phone.setText(user.getPhone());
            binding.address.setText(user.getAddress());
            Glide.with(this)
                    .load(user.getAvatar())
                    .transform(new CircleCrop())
                    .into(binding.profileImage);

            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapProfile);
            if (mapFragment != null){
                mapFragment.getMapAsync(googleMap -> {
                    googleMap.addMarker(new MarkerOptions().position(latLng).title("Tu ubicación"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
                });
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}