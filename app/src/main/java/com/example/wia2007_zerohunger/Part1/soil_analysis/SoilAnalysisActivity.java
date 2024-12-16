package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.Part1.soil_analysis.reader.CropFilter;
import com.example.wia2007_zerohunger.Part1.soil_analysis.reader.TextFileCropReader;
import com.example.wia2007_zerohunger.R;

import java.util.ArrayList;
import java.util.List;

public class SoilAnalysisActivity extends AppCompatActivity {

    Button soilBackButton, soilSubmitButton;
    EditText soilNitrogenEditText, soilPhosphorusEditText, soilPotassiumEditText;
    EditText soilPHValueEditText, soilTemperatureEditText, soilHumidityEditText, soilRainfallEditText;

    List<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_soil_analysis);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        soilNitrogenEditText = findViewById(R.id.soilNitrogenEditText);
        soilPhosphorusEditText = findViewById(R.id.soilPhosphorusEditText);
        soilPotassiumEditText = findViewById(R.id.soilPotassiumEditText);
        soilPHValueEditText = findViewById(R.id.soilPHValueEditText);
        soilTemperatureEditText = findViewById(R.id.soilTemperatureEditText);
        soilHumidityEditText = findViewById(R.id.soilHumidityEditText);

        soilBackButton = findViewById(R.id.soilBackButton);
        soilSubmitButton = findViewById(R.id.soilSubmitButton);


        soilBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        soilSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoilAnalysisActivity.this, CropResultActivity.class);
                //intent.putExtra("cropResultList", (ArrayList<String>) result);
                startActivity(intent);
            }
        });
    }

    public void generateCropResultList() {
        TextFileCropReader textFileCropReader = new TextFileCropReader();
        List<CropData> cropDataList = textFileCropReader.readTextFile(this, R.raw.crop_recommendation);

        CropFilter cropFilter = new CropFilter();
        result = cropFilter.getLabelForConditions(cropDataList,
                Double.parseDouble(soilNitrogenEditText.getText().toString()),
                Double.parseDouble(soilPhosphorusEditText.getText().toString()),
                Double.parseDouble(soilPotassiumEditText.getText().toString()),
                Double.parseDouble(soilTemperatureEditText.getText().toString()),
                Double.parseDouble(soilHumidityEditText.getText().toString()),
                Double.parseDouble(soilPHValueEditText.getText().toString()),
                Double.parseDouble(soilRainfallEditText.getText().toString()));
    }
}