package com.example.wia2007_zerohunger.Part5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance4FMT extends AppCompatActivity {

    private EditText medicalInsuranceLabel;
    private EditText travelInsuranceLabel;
    private EditText otherInsuranceLabel;
    private TextView totalInsuranceLabel;
    private Button backButton;
    private Button applyButton;
    private double lifeInsuranceCost;
    private double motorInsuranceCost;
    private double personalInsuranceCost;
    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "InsurancePrefs";
    private InsuranceDatabaseHelperMP5 insuranceDatabaseHelper;

    private long id; // ID of the row to update

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_insurance4_fmt);

        insuranceDatabaseHelper = new InsuranceDatabaseHelperMP5(this);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Initialize views
        medicalInsuranceLabel = findViewById(R.id.medicalInsuranceLabel);
        travelInsuranceLabel = findViewById(R.id.travelInsuranceLabel);
        otherInsuranceLabel = findViewById(R.id.otherInsuranceLabel);
        totalInsuranceLabel = findViewById(R.id.totalInsuranceLabel);
        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);

        // Retrieve the ID and values from the intent
        id = getIntent().getLongExtra("id", -1);
        lifeInsuranceCost = getIntent().getDoubleExtra("lifeInsuranceCost", 0.0);
        motorInsuranceCost = getIntent().getDoubleExtra("motorInsuranceCost", 0.0);
        personalInsuranceCost = getIntent().getDoubleExtra("personalInsuranceCost", 0.0);

        // Load saved data
        medicalInsuranceLabel.setText(sharedPreferences.getString("medicalInsuranceCost", ""));
        travelInsuranceLabel.setText(sharedPreferences.getString("travelInsuranceCost", ""));
        otherInsuranceLabel.setText(sharedPreferences.getString("otherInsuranceCost", ""));

        // Set click listener for the Apply button
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Parse input values
                double medicalInsurance = parseDouble(medicalInsuranceLabel.getText().toString());
                double travelInsurance = parseDouble(travelInsuranceLabel.getText().toString());
                double otherInsurance = parseDouble(otherInsuranceLabel.getText().toString());

                // Calculate total insurance costs including values from P5_Insurance3FMT
                double totalInsuranceCosts = lifeInsuranceCost + motorInsuranceCost + personalInsuranceCost +
                        medicalInsurance + travelInsurance + otherInsurance;

                // Display the result
                totalInsuranceLabel.setText(String.format("RM %.2f", totalInsuranceCosts));

                // Update only the relevant fields in the database
                insuranceDatabaseHelper.updateInsuranceData(id, null, null, null, null, null, null, null, null, null, medicalInsurance, travelInsurance, otherInsurance);

                // Navigate to the next activity
                Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance5FMT.class);
                intent.putExtra("id", id); // Pass the ID to the next activity
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Set click listener for the Back button
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(P5_Insurance4FMT.this, P5_Insurance3FMT.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });
    }

    private double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}