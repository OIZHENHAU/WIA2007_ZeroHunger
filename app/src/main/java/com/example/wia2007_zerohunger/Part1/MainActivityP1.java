package com.example.wia2007_zerohunger.Part1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.Part1.soil_analysis.MainSoilActivity;
import com.example.wia2007_zerohunger.Part1.view.MainWeatherActivity;
import com.example.wia2007_zerohunger.R;
import com.example.wia2007_zerohunger.databinding.ActivityMainP1Binding;

public class MainActivityP1 extends AppCompatActivity {

    ActivityMainP1Binding mainBinding;
    ImageButton weatherInfo;
    ImageButton soilInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainP1Binding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        Intent intent = getIntent();
        String nickname = intent.getStringExtra("nickName");

        mainBinding.textViewMainP1.setText("Welcome, " + nickname);

        mainBinding.mainP1Toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        mainBinding.imageButtonWeatherInfoP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityP1.this, MainWeatherActivity.class);
                startActivity(intent);
            }
        });

        mainBinding.imageButtonSoilInfoP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityP1.this, MainSoilActivity.class);
                startActivity(intent);
            }
        });


    }
}