package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

public class MainActivityPart5S5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s5);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button insuranceCalculator = findViewById(R.id.insuranceButton);
        Button savingsGuide = findViewById(R.id.SavingsGuideButton);
        Button backButton = findViewById(R.id.backButton);
        Button taxGuideButton = findViewById(R.id.TaxGuideButton);


        insuranceCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToInsurance(v);
            }
        });

        savingsGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToSavingsGuide(v);
            }
        });

        taxGuideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S5.this, P5_Tax1FMT.class);
                startActivity(intent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigationFromHomepageFMTToHomepage(v);
            }
        });
    }


    public void navigationFromHomepageFMTToInsurance(View view) {
        Intent intent = new Intent(MainActivityPart5S5.this, P5_Insurance1FMT.class);
        startActivity(intent);
    }

    public void navigationFromHomepageFMTToSavingsGuide(View view) {
        Intent intent = new Intent(MainActivityPart5S5.this, P5_SavingsGuide1FMT.class);
        startActivity(intent);
    }


    public void navigationFromHomepageFMTToHomepage(View view) {
        Intent intent = new Intent(MainActivityPart5S5.this, MainActivityPart5S1.class);
        startActivity(intent);
    }
}