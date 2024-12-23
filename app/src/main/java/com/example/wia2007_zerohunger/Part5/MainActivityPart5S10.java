package com.example.wia2007_zerohunger.Part5;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.Part5.DirectionHelper.FetchURL;
import com.example.wia2007_zerohunger.Part5.DirectionHelper.TaskLoadedCallback;
import com.example.wia2007_zerohunger.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MainActivityPart5S10 extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {

    Button backButtonP5S10, buttonGetDirectionP5S10;
    private MarkerOptions place1, place2;
    private Polyline currentPolyLine;
    private GoogleMap currentGoogleMap;
    LocationManager locationManagerP5S10;
    LocationListener locationListenerP5S10;
    double currentLat;
    double currentLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s10);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        backButtonP5S10 = findViewById(R.id.backButtonP5S10);
        buttonGetDirectionP5S10 = findViewById(R.id.buttonGetDirectionP5S10);

        backButtonP5S10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        locationManagerP5S10 = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListenerP5S10 = location -> {
            currentLat = location.getLatitude();
            currentLon = location.getLongitude();

        };

        Log.d("currentLatitude: ", String.valueOf(currentLat));
        Log.d("currentLongitude: ", String.valueOf(currentLon));

        double financialLatitude = intent.getDoubleExtra("financialLatitude", 0);
        double financialLongitude = intent.getDoubleExtra("financialLongitude", 0);

        Log.d("financialLatitudeP5S10: ", String.valueOf(financialLatitude));
        Log.d("financialLongitudeP5S10: ", String.valueOf(financialLongitude));

        place1 = new MarkerOptions().position(new LatLng(currentLat, currentLon)).title("Current Location");
        place2 = new MarkerOptions().position(new LatLng(financialLatitude, financialLongitude)).title("Financial Location");

        buttonGetDirectionP5S10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myLog", "Button Clicked");
                new FetchURL(MainActivityPart5S10.this).execute(getUrl(place1.getPosition(),
                        place2.getPosition(), "driving"), "driving");
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragP5S10);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        currentGoogleMap = googleMap;
        Log.d("mylog", "Added Markers");
        currentGoogleMap.addMarker(place1);
        currentGoogleMap.addMarker(place2);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyLine != null)
            currentPolyLine.remove();
        currentPolyLine = currentGoogleMap.addPolyline((PolylineOptions) values[0]);
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + "AIzaSyBR-at9ijSv7uoKZGVBDAEQ_3JCbNANNBw";
        return url;
    }
}