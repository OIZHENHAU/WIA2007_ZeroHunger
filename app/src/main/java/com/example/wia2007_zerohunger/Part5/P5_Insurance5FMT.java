package com.example.wia2007_zerohunger.Part5;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Insurance5FMT extends AppCompatActivity {

    private TextView lifeInsuranceDeductibleLabel;
    private TextView motorInsuranceDeductibleLabel;
    private TextView personalInsuranceDeductibleLabel;
    private TextView medicalInsuranceDeductibleLabel;
    private TextView travelInsuranceDeductibleLabel;
    private TextView otherInsuranceDeductibleLabel;
    private TextView lifeInsuranceCostLabel;
    private TextView motorInsuranceCostLabel;
    private TextView personalInsuranceCostLabel;
    private TextView medicalInsuranceCostLabel;
    private TextView travelInsuranceCostLabel;
    private TextView otherInsuranceCostLabel;
    private TextView insurancePayableLabel;
    private TextView totalDeductiblesLabel;
    private TextView totalInsuranceCostLabel;
    private Button backButton;
    private Button homeButton;
    private InsuranceDatabaseHelperMP5 insuranceDatabaseHelper;

    private long id; // ID of the row to retrieve

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_insurance5_fmt);

        insuranceDatabaseHelper = new InsuranceDatabaseHelperMP5(this);

        // Initialize TextViews
        lifeInsuranceDeductibleLabel = findViewById(R.id.lifeInsuranceDeductibleLabel);
        motorInsuranceDeductibleLabel = findViewById(R.id.motorInsuranceDeductibleLabel);
        personalInsuranceDeductibleLabel = findViewById(R.id.personalInsuranceDeductibleLabel);
        medicalInsuranceDeductibleLabel = findViewById(R.id.medicalInsuranceDeductibleLabel);
        travelInsuranceDeductibleLabel = findViewById(R.id.travelInsuranceDeductibleLabel);
        otherInsuranceDeductibleLabel = findViewById(R.id.otherInsuranceDeductibleLabel);
        lifeInsuranceCostLabel = findViewById(R.id.lifeInsuranceCostLabel);
        motorInsuranceCostLabel = findViewById(R.id.motorInsuranceCostLabel);
        personalInsuranceCostLabel = findViewById(R.id.personalInsuranceCostLabel);
        medicalInsuranceCostLabel = findViewById(R.id.medicalInsuranceCostLabel);
        travelInsuranceCostLabel = findViewById(R.id.travelInsuranceCostLabel);
        otherInsuranceCostLabel = findViewById(R.id.otherInsuranceCostLabel);
        insurancePayableLabel = findViewById(R.id.insurancePayableLabel);
        totalDeductiblesLabel = findViewById(R.id.totalDeductiblesLabel);
        totalInsuranceCostLabel = findViewById(R.id.totalInsuranceCostLabel);

        // Initialize Buttons
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve the ID from the intent
        id = getIntent().getLongExtra("id", -1);

        // Retrieve and display data from the database
        displayInsuranceSummary();

        // Set up button click listeners
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_Insurance5FMT.this, P5_Insurance4FMT.class);
            startActivity(intent);
            finish(); // Close the current activity
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(P5_Insurance5FMT.this, MainActivityPart5S5.class);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }

    // Inside P5_Insurance5FMT.java
    private void displayInsuranceSummary() {
        // Retrieve data using the ID
        Cursor cursor = insuranceDatabaseHelper.getInsuranceDataById(id);
        if (cursor.moveToFirst()) {
            // Retrieve column indices using the correct column names
            int lifeInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_LIFE_INSURANCE_DEDUCTIBLE);
            int motorInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_MOTOR_INSURANCE_DEDUCTIBLE);
            int personalInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_PERSONAL_INSURANCE_DEDUCTIBLE);
            int medicalInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_MEDICAL_INSURANCE_DEDUCTIBLE);
            int travelInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_TRAVEL_INSURANCE_DEDUCTIBLE);
            int otherInsuranceDeductibleIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_OTHER_INSURANCE_DEDUCTIBLE);
            int lifeInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_LIFE_INSURANCE_COST);
            int motorInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_MOTOR_INSURANCE_COST);
            int personalInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_PERSONAL_INSURANCE_COST);
            int medicalInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_MEDICAL_INSURANCE_COST);
            int travelInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_TRAVEL_INSURANCE_COST);
            int otherInsuranceCostIndex = cursor.getColumnIndex(InsuranceDatabaseHelperMP5.COLUMN_OTHER_INSURANCE_COST);

            // Retrieve values from the cursor, defaulting to 0.0 if the column is not found
            double lifeInsuranceDeductible = lifeInsuranceDeductibleIndex != -1 ? cursor.getDouble(lifeInsuranceDeductibleIndex) : 0.0;
            double motorInsuranceDeductible = motorInsuranceDeductibleIndex != -1 ? cursor.getDouble(motorInsuranceDeductibleIndex) : 0.0;
            double personalInsuranceDeductible = personalInsuranceDeductibleIndex != -1 ? cursor.getDouble(personalInsuranceDeductibleIndex) : 0.0;
            double medicalInsuranceDeductible = medicalInsuranceDeductibleIndex != -1 ? cursor.getDouble(medicalInsuranceDeductibleIndex) : 0.0;
            double travelInsuranceDeductible = travelInsuranceDeductibleIndex != -1 ? cursor.getDouble(travelInsuranceDeductibleIndex) : 0.0;
            double otherInsuranceDeductible = otherInsuranceDeductibleIndex != -1 ? cursor.getDouble(otherInsuranceDeductibleIndex) : 0.0;
            double lifeInsuranceCost = lifeInsuranceCostIndex != -1 ? cursor.getDouble(lifeInsuranceCostIndex) : 0.0;
            double motorInsuranceCost = motorInsuranceCostIndex != -1 ? cursor.getDouble(motorInsuranceCostIndex) : 0.0;
            double personalInsuranceCost = personalInsuranceCostIndex != -1 ? cursor.getDouble(personalInsuranceCostIndex) : 0.0;
            double medicalInsuranceCost = medicalInsuranceCostIndex != -1 ? cursor.getDouble(medicalInsuranceCostIndex) : 0.0;
            double travelInsuranceCost = travelInsuranceCostIndex != -1 ? cursor.getDouble(travelInsuranceCostIndex) : 0.0;
            double otherInsuranceCost = otherInsuranceCostIndex != -1 ? cursor.getDouble(otherInsuranceCostIndex) : 0.0;

            // Calculate totals
            double totalDeductibles = lifeInsuranceDeductible + motorInsuranceDeductible + personalInsuranceDeductible +
                    medicalInsuranceDeductible + travelInsuranceDeductible + otherInsuranceDeductible;
            double totalInsuranceCost = lifeInsuranceCost + motorInsuranceCost + personalInsuranceCost +
                    medicalInsuranceCost + travelInsuranceCost + otherInsuranceCost;
            double insurancePayable = totalDeductibles + totalInsuranceCost;

            // Display the results
            lifeInsuranceDeductibleLabel.setText(String.format("RM %.2f", lifeInsuranceDeductible));
            motorInsuranceDeductibleLabel.setText(String.format("RM %.2f", motorInsuranceDeductible));
            personalInsuranceDeductibleLabel.setText(String.format("RM %.2f", personalInsuranceDeductible));
            medicalInsuranceDeductibleLabel.setText(String.format("RM %.2f", medicalInsuranceDeductible));
            travelInsuranceDeductibleLabel.setText(String.format("RM %.2f", travelInsuranceDeductible));
            otherInsuranceDeductibleLabel.setText(String.format("RM %.2f", otherInsuranceDeductible));
            lifeInsuranceCostLabel.setText(String.format("RM %.2f", lifeInsuranceCost));
            motorInsuranceCostLabel.setText(String.format("RM %.2f", motorInsuranceCost));
            personalInsuranceCostLabel.setText(String.format("RM %.2f", personalInsuranceCost));
            medicalInsuranceCostLabel.setText(String.format("RM %.2f", medicalInsuranceCost));
            travelInsuranceCostLabel.setText(String.format("RM %.2f", travelInsuranceCost));
            otherInsuranceCostLabel.setText(String.format("RM %.2f", otherInsuranceCost));
            totalDeductiblesLabel.setText(String.format("RM %.2f", totalDeductibles));
            totalInsuranceCostLabel.setText(String.format("RM %.2f", totalInsuranceCost));
            insurancePayableLabel.setText(String.format("RM %.2f", insurancePayable));
        }
        cursor.close();
    }
}