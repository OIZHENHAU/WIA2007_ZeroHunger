package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.Part1.MainActivityP1;
import com.example.wia2007_zerohunger.R;
import com.example.wia2007_zerohunger.databinding.ActivityMainSoilBinding;

public class MainSoilActivity extends AppCompatActivity {

    ActivityMainSoilBinding mainSoilBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        mainSoilBinding = ActivityMainSoilBinding.inflate(getLayoutInflater());
        setContentView(mainSoilBinding.getRoot());

        mainSoilBinding.soilP1Toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(MainSoilActivity.this, MainActivityP1.class);
            startActivity(intent);
        });

        mainSoilBinding.soilAnalysisButtonP1.setOnClickListener(v -> {
            Intent intent = new Intent(MainSoilActivity.this, SoilAnalysisActivity.class);
            startActivity(intent);
        });

        mainSoilBinding.cropRecommentButtonP1.setOnClickListener(v -> {
            Intent intent = new Intent(MainSoilActivity.this, CropRecommendationActivity.class);
            startActivity(intent);
        });

    }

    //haha
}