package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

public class MainActivityPart5S3 extends AppCompatActivity {

    EditText nameEditTextP5S3, minAmountEditTextP5S3, minSlotsEditTextP5S3;
    Button calculateButtonP5S3, donateButtonP5S3;
    TextView sumUpAmountTextViewP5S3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameEditTextP5S3 = findViewById(R.id.nameEditTextP5S3);
        minAmountEditTextP5S3 = findViewById(R.id.minAmountEditTextP5S3);
        minSlotsEditTextP5S3 = findViewById(R.id.minSlotsEditTextP5S3);

        sumUpAmountTextViewP5S3 = findViewById(R.id.sumUpAmountTextViewP5S3);

        calculateButtonP5S3 = findViewById(R.id.calculateButtonP5S3);
        donateButtonP5S3 = findViewById(R.id.donateButtonP5S3);

        Intent intent = getIntent();

        String aidName = intent.getStringExtra("aidName");
        int donationAmount = intent.getIntExtra("donationAmount", 0);
        int availableSlot = intent.getIntExtra("availableSlot", 0);
        String aidDateLine = intent.getStringExtra("aidDateLine");
        int financialID = intent.getIntExtra("financialID", 0);
        int imageID = intent.getIntExtra("imageID", 0);

        Log.d("aidNameP5S3: ", aidName);
        Log.d("donationAmountP5S3: ", String.valueOf(donationAmount));
        Log.d("availableSlotP5S3: ", String.valueOf(availableSlot));
        Log.d("aidDateLineP5S3: ", aidDateLine);
        Log.d("financialIDP5S3: ", String.valueOf(financialID));
        Log.d("imageIDP5S3: ", String.valueOf(imageID));


        calculateButtonP5S3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!minAmountEditTextP5S3.getText().toString().isEmpty() &&
                        !minSlotsEditTextP5S3.getText().toString().isEmpty()) {
                    String minAmount = minAmountEditTextP5S3.getText().toString();
                    String minSlots = minSlotsEditTextP5S3.getText().toString();

                    int minAmountInt = Integer.parseInt(minAmount);
                    int minSlotsInt = Integer.parseInt(minSlots);
                    int sumAmount = minAmountInt * minSlotsInt;

                    String sumUpDescription = "RM " + minAmount + " x " + minSlots + " = RM " + sumAmount;
                    sumUpAmountTextViewP5S3.setText(sumUpDescription);

                } else {
                    Toast.makeText(MainActivityPart5S3.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                }
            }
        });

        donateButtonP5S3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!minAmountEditTextP5S3.getText().toString().isEmpty() &&
                        !minSlotsEditTextP5S3.getText().toString().isEmpty()) {
                    int minUserAmount = Integer.parseInt(minAmountEditTextP5S3.getText().toString());
                    int minUserSlots = Integer.parseInt(minSlotsEditTextP5S3.getText().toString());

                    Intent intent = new Intent(MainActivityPart5S3.this, MainActivityPart5S4.class);
                    intent.putExtra("minUserAmount", minUserAmount);
                    intent.putExtra("minUserSlots", minUserSlots);

                    intent.putExtra("aidName", aidName);
                    intent.putExtra("donationAmount", donationAmount);
                    intent.putExtra("availableSlot", availableSlot);
                    intent.putExtra("aidDateLine", aidDateLine);
                    intent.putExtra("financialID", financialID);
                    intent.putExtra("imageID", imageID);

                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivityPart5S3.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

}