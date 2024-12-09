package com.example.wia2007_zerohunger.Part1.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.wia2007_zerohunger.databinding.ActivityWeatherBinding;
import com.example.wia2007_zerohunger.Part1.model.WeatherModel;
import com.example.wia2007_zerohunger.Part1.util.Constants;
import com.example.wia2007_zerohunger.Part1.viewmodel.WeatherViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class WeatherActivity extends AppCompatActivity {

    ActivityWeatherBinding weatherBinding;
    String prefer;
    WeatherViewModel weatherViewModel;
    LocationManager locationManager;
    LocationListener locationListener;
    double lat;
    double lon;

    String countryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherBinding = ActivityWeatherBinding.inflate(getLayoutInflater());
        setContentView(weatherBinding.getRoot());

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        weatherBinding.linearLayoutWeatherData.setVisibility(View.INVISIBLE);
        prefer = getIntent().getStringExtra(Constants.intentName);

        if (prefer != null) {
            if (prefer.equals(Constants.byLocation)) {
                //get weather by location
                weatherBinding.linearLayoutSearch.setVisibility(View.INVISIBLE);
                getWeatherDataByLocation();
            } else {
                //get weather by city name
                weatherBinding.progressBarWeatherData.setVisibility(View.INVISIBLE);
            }
        }


        weatherBinding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        weatherBinding.search.setOnClickListener(v -> {
            getWeatherDataByCityName();
        });

        weatherBinding.buttonNextFourDays.setOnClickListener(v -> {
            Intent intent = new Intent(WeatherActivity.this, WeatherNext4Activity.class);
            intent.putExtra("countryName", countryName);
            startActivity(intent);
        });
    }

    public void getWeatherDataByCityName() {
        String cityName = weatherBinding.editTextCityName.getText().toString();

        if (cityName.isEmpty()) {
            Toast.makeText(this, "Please Enter City Name", Toast.LENGTH_SHORT).show();

        } else {
            weatherViewModel.getProgressBarLiveData().observe(WeatherActivity.this, progressState -> {
                if (progressState) {
                    weatherBinding.progressBarWeatherData.setVisibility(View.VISIBLE);
                    weatherBinding.linearLayoutWeatherData.setVisibility(View.INVISIBLE);
                } else {
                    weatherBinding.progressBarWeatherData.setVisibility(View.INVISIBLE);
                }
            });

            weatherViewModel.sendRequestByCityName(getApplicationContext(), cityName);
            weatherViewModel.getWeatherResponseLiveData().observe(WeatherActivity.this, this::showWeatherData);
        }
    }

    @SuppressLint("MissingPermission")
    public void getWeatherDataByLocation() {
        weatherBinding.linearLayoutSearch.setVisibility(View.INVISIBLE);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = location -> {

            lat = location.getLatitude();
            lon = location.getLongitude();

            Log.d("userLatitude: ", String.valueOf(lat));
            Log.d("userLongitude: ", String.valueOf(lon));

            weatherViewModel.getProgressBarLiveData().observe(WeatherActivity.this, progressState -> {
                if (progressState) {
                    weatherBinding.progressBarWeatherData.setVisibility(View.VISIBLE);
                    weatherBinding.linearLayoutWeatherData.setVisibility(View.INVISIBLE);
                } else {
                    weatherBinding.progressBarWeatherData.setVisibility(View.INVISIBLE);
                }
            });

            //call the sendRequestByLocation method of the WeatherViewModel
            weatherViewModel.sendRequestByLocation(getApplicationContext(),lat,lon);
            //show data in the UI
            weatherViewModel.getWeatherResponseLiveData().observe(WeatherActivity.this, this::showWeatherData);

        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 50, locationListener);
    }

    public void showWeatherData(WeatherModel response) {

        weatherBinding.textViewCityName.setText(response.getName() + ", " + response.getSys().getCountry());
        countryName = response.getName() + ", " + response.getSys().getCountry();
        weatherBinding.textViewTemperature.setText(response.getMain().getTemp() + "°C");
        weatherBinding.textViewDescription.setText(response.getWeather().get(0).getDescription());
        weatherBinding.textViewHumidity.setText(" : " + response.getMain().getHumidity() + "%");
        weatherBinding.textViewMinTemp.setText(" : " + response.getMain().getTemp_min() + "°C");
        weatherBinding.textViewMaxTemp.setText(" : " + response.getMain().getTemp_max() + "°C");
        weatherBinding.textViewPressure.setText(" : " + response.getMain().getPressure() + "hPa");
        weatherBinding.textViewWind.setText(" : " + response.getWind().getSpeed() + "m/s");

        weatherBinding.linearLayoutWeatherData.setVisibility(View.VISIBLE);
        weatherBinding.progressBarWeatherIcon.setVisibility(View.VISIBLE);

        //https://openweathermap.org/img/wn/11n@2x.png
        String iconCode = response.getWeather().get(0).getIcon();
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                .into(weatherBinding.imageViewWeatherIcon, new Callback() {
                    @Override
                    public void onSuccess() {
                        weatherBinding.progressBarWeatherIcon.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {
                        weatherBinding.imageViewWeatherIcon.setImageResource(R.drawable.partly_cloudy_day);
                        Log.d("iconError: ", e.getLocalizedMessage());
                        weatherBinding.progressBarWeatherIcon.setVisibility(View.INVISIBLE);
                    }
                });

    }
}