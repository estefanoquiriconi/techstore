package com.example.frontend.ui.cart;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.R;
import com.example.frontend.data.models.Order;
import com.example.frontend.data.models.OrderDetail;
import com.example.frontend.data.models.User;
import com.example.frontend.data.repositories.CartRepository;
import com.example.frontend.data.services.ApiService;
import com.example.frontend.databinding.ActivityCheckoutBinding;
import com.example.frontend.utils.CurrencyUtils;
import com.example.frontend.utils.RetrofitClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity implements OnMapReadyCallback {

    private ActivityCheckoutBinding binding;
    private Random random;
    private ApiService apiService;
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LatLng userLocation;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 777;
    private CheckoutViewModel checkoutViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        this.apiService = RetrofitClient.getClient().create(ApiService.class);
        this.fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        checkoutViewModel = new ViewModelProvider(this).get(CheckoutViewModel.class);
        random = new Random();

        setupMap();
        setupListeners();
        loadUserData();
        String totalText = "Total a pagar: " + CurrencyUtils.formatToARCurrency(Double.parseDouble(getIntent().getStringExtra("total")));
        binding.totalAmountText.setText(totalText);

        checkoutViewModel.getUser().observe(this, user -> {
            binding.editTextAddress.setText(user.getAddress());
            binding.editTextPhone.setText(user.getPhone());
            Log.e("LATITUDE", String.valueOf(user.getLatitude()));
            if (user.getLatitude() != 0 && user.getLongitude() != 0) {
                updateMap(new LatLng(user.getLatitude(), user.getLongitude()), user.getAddress());
            }
        });

        binding.payButton.setOnClickListener(v -> {
            if (validateFields()) {
                showProcessingDialog();
                paymentRequest();
            }
        });
    }

    private void loadUserData() {
        checkoutViewModel.loadUser(getUserId());
    }

    private int getUserId() {
        return this.getSharedPreferences("userPreferences", Context.MODE_PRIVATE).getInt("user_id", 1);
    }

    private void initUI() {
        EdgeToEdge.enable(this);
        binding = ActivityCheckoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    private void setupListeners() {
        binding.mapSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchLocation(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        binding.floatingActionButtonMyLocation.setOnClickListener(v -> {
            googleMap.clear();
            getCurrentLocation();
        });
    }

    private void searchLocation(String location) {
        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> addressList = geocoder.getFromLocationName(location, 1);
            if (addressList != null && !addressList.isEmpty()) {
                Address address = addressList.get(0);
                updateMap(new LatLng(address.getLatitude(), address.getLongitude()), location);
            }
        } catch (IOException e) {
            Toast.makeText(this, "Error al buscar ubicación", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, location -> {
                        userLocation = location != null ? new LatLng(location.getLatitude(), location.getLongitude()) : defaultLatLng();
                        updateMap(userLocation, "Su ubicación actual");
                    });
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.googleMap.setOnMapClickListener(latLng -> updateMap(latLng, "Su ubicación seleccionada"));
    }

    private LatLng defaultLatLng() {
        return new LatLng(-27.783370, -64.264183);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        }
    }

    private void updateMap(LatLng latLng, String title) {
        this.userLocation = latLng;
        if (googleMap != null) {
            googleMap.clear();
            googleMap.addMarker(new MarkerOptions().position(latLng).title(title));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
        }
    }

    private void paymentRequest() {
        new Handler().postDelayed(() -> {
            boolean isSuccess = random.nextBoolean();

            ProcessingDialogFragment dialog = (ProcessingDialogFragment) getSupportFragmentManager().findFragmentByTag("ProcessingDialog");
            if (dialog != null) {
                dialog.dismiss();
            }

            showPaymentResult(isSuccess);
        }, 3000);
    }

    private boolean validateFields() {
        if (Objects.requireNonNull(binding.cardNumberInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.expiryDateInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.cvvInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.cardHolderInput.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.editTextAddress.getText()).toString().isEmpty() ||
                Objects.requireNonNull(binding.editTextPhone.getText()).toString().isEmpty()
        ) {
            Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void showPaymentResult(boolean isSuccess) {
        if (isSuccess) {
            createOrder();
            updateUser();
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("¡Pago rechazado!")
                    .setMessage("Lo entimos, su pago ha sido rechazado. Intente nuevamente")
                    .setPositiveButton("Aceptar", null)
                    .show();
        }
    }

    private void createOrder() {
        CartRepository cartRepository = new CartRepository(this, apiService);
        Map<Integer, Integer> productQuantities = cartRepository.getCartProductsWithQuantities();

        Order order = new Order();
        order.setUserId(getUserId());
        order.setTotalAmount(Double.parseDouble(getIntent().getStringExtra("total")));
        order.setOrderDetails(createOrderDetails(productQuantities));

        apiService.saveOrderWithDetails(order).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(@NonNull Call<Order> call, @NonNull Response<Order> response) {
                if (response.isSuccessful()) {
                    cartRepository.clearCart();
                    new AlertDialog.Builder(CheckoutActivity.this)
                            .setTitle("¡Pago exitoso!")
                            .setMessage("Su pago ha sido procesado correctamente")
                            .setPositiveButton("Aceptar", (dialog, which) -> finish())
                            .show();
                } else if (response.code() == 400) {
                    new AlertDialog.Builder(CheckoutActivity.this)
                            .setTitle("Stock no disponible")
                            .setMessage("Algunos productos de tu carrito no tienen suficiente stock disponible. Por favor, revísalos antes de continuar.")
                            .setPositiveButton("Entendido", (dialog, which) -> finish())
                            .show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Order> call, @NonNull Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error al guardar el pedido: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<OrderDetail> createOrderDetails(Map<Integer, Integer> productQuantities) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            orderDetails.add(new OrderDetail(entry.getKey(), entry.getValue()));
        }
        return orderDetails;
    }

    private void updateUser() {
        String address = Objects.requireNonNull(binding.editTextAddress.getText()).toString().trim();
        String phone = Objects.requireNonNull(binding.editTextPhone.getText()).toString().trim();

        if (address.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User();
        user.setAddress(address);
        user.setLatitude(userLocation.latitude);
        user.setLongitude(userLocation.longitude);
        user.setPhone(phone);

        apiService.updateUser(getUserId(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(CheckoutActivity.this, "Error al actualizar el usuario.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(CheckoutActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProcessingDialog() {
        ProcessingDialogFragment processingDialog = new ProcessingDialogFragment();
        processingDialog.setCancelable(false);
        processingDialog.show(getSupportFragmentManager(), "ProcessingDialog");
    }
}

