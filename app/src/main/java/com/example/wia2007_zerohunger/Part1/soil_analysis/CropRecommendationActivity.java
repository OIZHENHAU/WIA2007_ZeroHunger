package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class CropRecommendationActivity extends AppCompatActivity {

    Button cropBackButton, cropSubmitButton;
    EditText cropNitrogenEditText, cropPhosphorusEditText, cropPotassiumEditText;
    EditText cropPHValueEditText, cropTemperatureEditText, cropHumidityEditText, cropRainfallEditText;

    List<String> result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crop_recommendation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cropNitrogenEditText = findViewById(R.id.cropNitrogenEditText);
        cropPhosphorusEditText = findViewById(R.id.cropPhosphorusEditText);
        cropPotassiumEditText = findViewById(R.id.cropPotassiumEditText);
        cropPHValueEditText = findViewById(R.id.cropPHValueEditText);
        cropTemperatureEditText = findViewById(R.id.cropTemperatureEditText);
        cropHumidityEditText = findViewById(R.id.cropHumidityEditText);
        cropRainfallEditText = findViewById(R.id.cropRainfallEditText);

        cropBackButton = findViewById(R.id.cropBackButton);
        cropSubmitButton = findViewById(R.id.cropSubmitButton);


        cropBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cropSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cropNitrogenEditText.getText().toString().isEmpty() || cropPhosphorusEditText.getText().toString().isEmpty() ||
                        cropPotassiumEditText.getText().toString().isEmpty() || cropPHValueEditText.getText().toString().isEmpty() ||
                        cropTemperatureEditText.getText().toString().isEmpty() || cropHumidityEditText.getText().toString().isEmpty() ||
                        cropRainfallEditText.getText().toString().isEmpty())  {
                    Toast.makeText(CropRecommendationActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(CropRecommendationActivity.this, CropResultActivity.class);
                    //intent.putExtra("cropResultList", (ArrayList<String>) result);
                    startActivity(intent);
                }
            }
        });
    }

    public void generateCropResultList() {
        TextFileCropReader textFileCropReader = new TextFileCropReader();
        List<CropData> cropDataList = textFileCropReader.readTextFile(this, R.raw.crop_recommendation);

        CropFilter cropFilter = new CropFilter();
        result = cropFilter.getLabelForConditions(cropDataList,
                Double.parseDouble(cropNitrogenEditText.getText().toString()),
                Double.parseDouble(cropPhosphorusEditText.getText().toString()),
                Double.parseDouble(cropPotassiumEditText.getText().toString()),
                Double.parseDouble(cropTemperatureEditText.getText().toString()),
                Double.parseDouble(cropHumidityEditText.getText().toString()),
                Double.parseDouble(cropPHValueEditText.getText().toString()),
                Double.parseDouble(cropRainfallEditText.getText().toString()));
    }
}