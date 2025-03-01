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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class P5_Tax5FMT extends AppCompatActivity {
    private TextView taxPayableLabel;
    private TextView incomeTaxLabel;
    private TextView taxRebateReliefLabel;
    private TextView taxDeductionAnnualLabel;
    private TextView takafulZakatLabel;
    private TextView roadTaxLabel;
    private TextView propertyTaxLabel;
    private TextView quitRentLabel;
    private TextView otherTaxLabel;
    private Button backButton;
    private Button homeButton;
    private TaxDatabaseHelper taxDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p5_tax5_fmt);

        taxDatabaseHelper = new TaxDatabaseHelper(this);

        // Initialize TextViews
        taxPayableLabel = findViewById(R.id.taxPayableLabel);
        incomeTaxLabel = findViewById(R.id.incomeTaxLabel);
        taxRebateReliefLabel = findViewById(R.id.taxRebateReliefLabel);
        taxDeductionAnnualLabel = findViewById(R.id.taxDeductionAnnualLabel);
        takafulZakatLabel = findViewById(R.id.takafulZakatLabel);
        roadTaxLabel = findViewById(R.id.roadTaxLabel);
        propertyTaxLabel = findViewById(R.id.propertyTaxLabel);
        quitRentLabel = findViewById(R.id.quitRentLabel);
        otherTaxLabel = findViewById(R.id.otherTaxLabel);

        // Initialize Buttons
        backButton = findViewById(R.id.backButton);
        homeButton = findViewById(R.id.homeButton);

        // Retrieve and display data from the database
        displayTaxSummary();

        // Set up button click listeners
        backButton.setOnClickListener(v -> {
            navigationFromTax5FMTToTax4FMT(v);
        });

        homeButton.setOnClickListener(v -> {
            navigationFromTax5FMTToHomepage(v);
        });
    }

    private void displayTaxSummary() {
        Cursor cursor = taxDatabaseHelper.getTaxData();
        if (cursor.moveToFirst()) {
            // Retrieve column indices
            int incomeTaxIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_INCOME_TAX);
            int taxReliefIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_TAX_RELIEF);
            int monthlyTaxDeductionIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_MONTHLY_TAX_DEDUCTION);
            int takafulZakatIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_TAKAFUL_ZAKAT);
            int roadTaxIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_ROAD_TAX);
            int propertyTaxIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_PROPERTY_TAX);
            int quitRentIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_QUIT_RENT);
            int otherTaxIndex = cursor.getColumnIndex(TaxDatabaseHelper.COLUMN_OTHER_TAX);

            // Retrieve values from the cursor, defaulting to 0.0 if the column is not found
            double incomeTax = incomeTaxIndex != -1 ? cursor.getDouble(incomeTaxIndex) : 0.0;
            double taxRelief = taxReliefIndex != -1 ? cursor.getDouble(taxReliefIndex) : 0.0;
            double monthlyTaxDeduction = monthlyTaxDeductionIndex != -1 ? cursor.getDouble(monthlyTaxDeductionIndex) : 0.0;
            double takafulZakat = takafulZakatIndex != -1 ? cursor.getDouble(takafulZakatIndex) : 0.0;
            double roadTax = roadTaxIndex != -1 ? cursor.getDouble(roadTaxIndex) : 0.0;
            double propertyTax = propertyTaxIndex != -1 ? cursor.getDouble(propertyTaxIndex) : 0.0;
            double quitRent = quitRentIndex != -1 ? cursor.getDouble(quitRentIndex) : 0.0;
            double otherTax = otherTaxIndex != -1 ? cursor.getDouble(otherTaxIndex) : 0.0;

            // Calculate total tax payable
            double taxPayable = incomeTax - (monthlyTaxDeduction * 12) - takafulZakat - roadTax - propertyTax - quitRent - otherTax;
            double totalTaxPayable = taxPayable < 0 ? 0 : taxPayable;

            // Display the results
            taxPayableLabel.setText(String.format("RM %.2f", totalTaxPayable));
            incomeTaxLabel.setText(String.format("RM %.2f", incomeTax)); // Display incomeTax
            taxRebateReliefLabel.setText(String.format("RM %.2f", taxRelief));
            taxDeductionAnnualLabel.setText(String.format("RM %.2f", monthlyTaxDeduction * 12));
            takafulZakatLabel.setText(String.format("RM %.2f", takafulZakat));
            roadTaxLabel.setText(String.format("RM %.2f", roadTax));
            propertyTaxLabel.setText(String.format("RM %.2f", propertyTax));
            quitRentLabel.setText(String.format("RM %.2f", quitRent));
            otherTaxLabel.setText(String.format("RM %.2f", otherTax));
        }
        cursor.close();
    }

    public void navigationFromTax5FMTToTax4FMT(View view) {
        Intent intent = new Intent(P5_Tax5FMT.this, P5_Tax4FMT.class);
        startActivity(intent);
    }

    public void navigationFromTax5FMTToHomepage(View view) {
        Intent intent = new Intent(P5_Tax5FMT.this, MainActivityPart5S1.class);
        startActivity(intent);
    }
}