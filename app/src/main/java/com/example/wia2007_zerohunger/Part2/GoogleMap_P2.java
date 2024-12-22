package com.example.wia2007_zerohunger.Part2;

import android.Manifest;
import androidx.core.app.ActivityCompat;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.wia2007_zerohunger.databinding.ActivityGoogleMapP2Binding;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.example.wia2007_zerohunger.R;

public class GoogleMap_P2 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityGoogleMapP2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGoogleMapP2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Start location service
        Intent intent = new Intent(this, MyNavigationService_P2.class);
        startService(intent);

        // Check and request location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSION);
        } else {
            startLocationUpdates();
        }
    }

    private static final int REQUEST_LOCATION_PERMISSION = 1001;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, start location updates
                startLocationUpdates();
            } else {
                // Permission denied, show a message
                Toast.makeText(this, "Location permission is required for this feature.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void startLocationUpdates() {
        // Start location service
        Intent serviceIntent = new Intent(this, MyNavigationService_P2.class);
        startService(serviceIntent);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    private String realLocation = page21.savedTakeUpAddressForMap;
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Resolve the address to latitude and longitude
        LatLng locationLatLng = getCoordinatesFromAddress(realLocation);

        if (locationLatLng != null) {
            // Add a marker at the resolved location and move the camera
            mMap.addMarker(new MarkerOptions().position(locationLatLng).title("Marker at " + realLocation));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locationLatLng, 15)); // Zoom level 15 for city view
        } else {
            Log.e("MapError", "Failed to resolve address: " + realLocation);
            Toast.makeText(this, "Unable to find the location: " + realLocation, Toast.LENGTH_SHORT).show();
        }
    }

    // Method to resolve the address to LatLng
    private LatLng getCoordinatesFromAddress(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            if (addresses != null && !addresses.isEmpty()) {
                Address location = addresses.get(0);
                return new LatLng(location.getLatitude(), location.getLongitude());
            }
        } catch (IOException e) {
            Log.e("GeocoderError", "Error in geocoding address", e);
        }
        return null; // Return null if the address couldn't be resolved
    }
}