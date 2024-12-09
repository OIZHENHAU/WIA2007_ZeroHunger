package com.example.wia2007_zerohunger.Part1.view;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.databinding.ActivityWeatherNext4Binding;
import com.example.wia2007_zerohunger.Part1.model.WeatherForecast;
import com.example.wia2007_zerohunger.Part1.service.WeatherForecastAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class WeatherNext4Activity extends AppCompatActivity {

    ActivityWeatherNext4Binding weatherNext4Binding;
    private RecyclerView recyclerView;
    private ArrayList<WeatherForecast> weatherForecastList = new ArrayList<WeatherForecast>();
    private HashMap<String, String> weatherIconCodeList = new HashMap<String, String>();
    private WeatherForecastAdapter weatherForecastAdapter;
    public TextView textViewCountryName;
    public Random random = new Random();

    // Define a date formatter to format the output
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weatherNext4Binding = ActivityWeatherNext4Binding.inflate(getLayoutInflater());
        setContentView(weatherNext4Binding.getRoot());

        Intent intent = getIntent();
        String countryName = intent.getStringExtra("countryName");
        textViewCountryName = findViewById(R.id.textViewName);
        textViewCountryName.setText(countryName);

        generateWeatherIconCodeList();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(WeatherNext4Activity.this));

        weatherNext4Binding.toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        generateWeatherForecastNext4DaysList();

        weatherForecastAdapter = new WeatherForecastAdapter(weatherForecastList, WeatherNext4Activity.this);
        recyclerView.setAdapter(weatherForecastAdapter);
    }

    private void generateWeatherForecastNext4DaysList() {
        int fourDays = 5;

        for(int i = 1; i <= fourDays; i++) {
            int randomIndex = random.nextInt(weatherIconCodeList.size());
            List<String> keys = new ArrayList<>(weatherIconCodeList.keySet());

            String weatherIconCode = keys.get(randomIndex);
            String weatherDescription = weatherIconCodeList.get(weatherIconCode);
            LocalDate date = LocalDate.now().plusDays(i);
            String formattedDate = date.format(formatter);
            int min = 16;
            int max = 32;
            String randomTemperature = String.valueOf(random.nextInt((max - min) + 1) + min) + "°C";

            WeatherForecast weatherForecast = new WeatherForecast(formattedDate, weatherDescription, randomTemperature, weatherIconCode);
            weatherForecastList.add(weatherForecast);
        }

    }

    public void generateWeatherIconCodeList() {
        weatherIconCodeList.put("01n", "Clear Sky");
        weatherIconCodeList.put("02n", "Few Clouds");
        weatherIconCodeList.put("03n", "Scattered Clouds");
        weatherIconCodeList.put("04n", "Broken Clouds");
        weatherIconCodeList.put("09n", "Shower Rain");
        weatherIconCodeList.put("10n", "Rain");
        weatherIconCodeList.put("11n", "Thunderstorm");
        weatherIconCodeList.put("50n", "Mist");

    }
}