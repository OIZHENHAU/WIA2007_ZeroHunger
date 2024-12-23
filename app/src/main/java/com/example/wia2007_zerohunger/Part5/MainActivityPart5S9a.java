package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.wia2007_zerohunger.Part5.reader.FinancialData;
import com.example.wia2007_zerohunger.Part5.reader.FinancialFilter;
import com.example.wia2007_zerohunger.Part5.reader.TextFileFinancialReader;
import com.example.wia2007_zerohunger.R;

import java.util.List;

public class MainActivityPart5S9a extends AppCompatActivity {

    EditText nameEditTextP5S9a;
    EditText minAmountEditTextP5S9a;
    EditText maxAmountEditTextP5S9a;
    EditText minSlotsEditTextP5S9a;
    EditText maxSlotsEditTextP5S9a;
    Button applyButtonP5S9a, continueButtonP5S9a;

    List<FinancialData> financialDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s9a);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditTextP5S9a = findViewById(R.id.nameEditTextP5S9a);
        minAmountEditTextP5S9a = findViewById(R.id.minAmountEditTextP5S9a);
        maxAmountEditTextP5S9a = findViewById(R.id.maxAmountEditTextP5S9a);
        minSlotsEditTextP5S9a = findViewById(R.id.minSlotsEditTextP5S9a);
        maxSlotsEditTextP5S9a = findViewById(R.id.maxSlotsEditTextP5S9a);

        applyButtonP5S9a = findViewById(R.id.applyButtonP5S9a);
        continueButtonP5S9a = findViewById(R.id.continueButtonP5S9a);

        generateFinancialDataList();

        applyButtonP5S9a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nameEditTextP5S9a.getText().toString().isEmpty() || minAmountEditTextP5S9a.getText().toString().isEmpty()
                        || maxAmountEditTextP5S9a.getText().toString().isEmpty() || minSlotsEditTextP5S9a.getText().toString().isEmpty()
                            || maxSlotsEditTextP5S9a.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivityPart5S9a.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                } else {
                    String countryName = nameEditTextP5S9a.getText().toString();
                    int minAmount = Integer.parseInt(minAmountEditTextP5S9a.getText().toString());
                    int maxAmount = Integer.parseInt(maxAmountEditTextP5S9a.getText().toString());
                    int minSlots = Integer.parseInt(minSlotsEditTextP5S9a.getText().toString());
                    int maxSlots = Integer.parseInt(maxSlotsEditTextP5S9a.getText().toString());
                    Log.d("countryNameP5S9a", countryName);
                    Log.d("minAmountP5S9a", String.valueOf(minAmount));
                    Log.d("maxAmountP5S9a", String.valueOf(maxAmount));
                    Log.d("minSlotsP5S9a", String.valueOf(minSlots));
                    Log.d("maxSlotsP5S9a", String.valueOf(maxSlots));

                    FinancialFilter filter = new FinancialFilter();
                    List<FinancialData> result = filter.getFinancialByCondition(financialDataList, countryName, minAmount, maxAmount, minSlots, maxSlots);

                    if (result.isEmpty()) {
                        Toast.makeText(MainActivityPart5S9a.this, "No location found", Toast.LENGTH_SHORT).show();

                    } else {
                        Intent intent = new Intent(MainActivityPart5S9a.this, MainActivityPart5S9.class);
                        intent.putExtra("filter", 1);
                        intent.putExtra("countryName", countryName);
                        intent.putExtra("minAmount", minAmount);
                        intent.putExtra("maxAmount", maxAmount);
                        intent.putExtra("minSlots", minSlots);
                        intent.putExtra("maxSlots", maxSlots);
                        startActivity(intent);
                    }

                }
            }
        });

        continueButtonP5S9a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S9a.this, MainActivityPart5S9.class);
                startActivity(intent);
            }
        });

    }

    public void generateFinancialDataList() {
        TextFileFinancialReader textFileFinancialReader = new TextFileFinancialReader();
        financialDataList = textFileFinancialReader.readTextFile(this, R.raw.financial_aid);

    }
}