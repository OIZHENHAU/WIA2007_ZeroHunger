package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
        soilRainfallEditText = findViewById(R.id.soilRainfallEditText);

        soilRainfallEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilRainfallEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilHumidityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilHumidityEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilTemperatureEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilTemperatureEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilPHValueEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilPHValueEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilPotassiumEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilPotassiumEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilPhosphorusEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilPhosphorusEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        soilNitrogenEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    Double.parseDouble(s.toString());
                    soilNitrogenEditText.setError(null);

                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
                Log.d("On Clicked", "Submit button clicked");

                if (soilNitrogenEditText.getText().toString().isEmpty() || soilPhosphorusEditText.getText().toString().isEmpty() ||
                        soilPotassiumEditText.getText().toString().isEmpty() || soilPHValueEditText.getText().toString().isEmpty() ||
                        soilTemperatureEditText.getText().toString().isEmpty() || soilHumidityEditText.getText().toString().isEmpty() ||
                        soilRainfallEditText.getText().toString().isEmpty())  {
                    Log.d("Soil Analysis", "Submit button clicked case 1");
                    Toast.makeText(SoilAnalysisActivity.this, "Please fill all the fields", Toast.LENGTH_SHORT).show();

                } else {
                    Log.d("Soil Analysis", "Submit button clicked case 2");
                    generateCropResultList();
                    Log.d("crop Data List After filter", String.valueOf(result.size()));
                    Intent intent = new Intent(SoilAnalysisActivity.this, CropResultActivity.class);
                    intent.putStringArrayListExtra("cropDataList", (ArrayList<String>) result);
                    startActivity(intent);
                }

            }
        });
    }

    public void generateCropResultList() {
        TextFileCropReader textFileCropReader = new TextFileCropReader();
        List<CropData> cropDataList = textFileCropReader.readTextFile(this, R.raw.crop_recommendation);

        Log.d("crop Data List before filter", String.valueOf(cropDataList.size()));
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