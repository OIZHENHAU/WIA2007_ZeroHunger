package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

public class MainActivityPart5S4 extends AppCompatActivity {

    Button noButtonP5S4, yesButtonP5S4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        int minUserAmount = intent.getIntExtra("minUserAmount", 0);
        int minUserSlots = intent.getIntExtra("minUserSlots", 0);

        Log.d("minUserAmountP5S4: ", String.valueOf(minUserAmount));
        Log.d("minUserSlotsP5S4: ", String.valueOf(minUserSlots));

        String aidName = intent.getStringExtra("aidName");
        int donationAmount = intent.getIntExtra("donationAmount", 0);
        int availableSlot = intent.getIntExtra("availableSlot", 0);
        String aidDateLine = intent.getStringExtra("aidDateLine");
        int financialID = intent.getIntExtra("financialID", 0);
        int imageID = intent.getIntExtra("imageID", 0);

        Log.d("aidNameP5S4: ", aidName);
        Log.d("donationAmountP5S4: ", String.valueOf(donationAmount));
        Log.d("availableSlotP5S4: ", String.valueOf(availableSlot));
        Log.d("aidDateLineP5S4: ", aidDateLine);
        Log.d("financialIDP5S4: ", String.valueOf(financialID));
        Log.d("imageIDP5S4: ", String.valueOf(imageID));

        noButtonP5S4 = findViewById(R.id.noButtonP5S4);
        yesButtonP5S4 = findViewById(R.id.yesButtonP5S4);

        noButtonP5S4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityPart5S4.this, "No donation has made.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivityPart5S4.this, MainActivityPart5S2.class);
                startActivity(intent);
            }
        });

        yesButtonP5S4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityPart5S4.this, "Donation has made.", Toast.LENGTH_SHORT).show();
                updateNote(minUserAmount, minUserSlots, donationAmount, availableSlot, financialID, aidName, aidDateLine, imageID);
            }
        });
    }

    private void updateNote(int minUserAmount, int minUserSlots, int donationAmount,
                            int availableSlot, int financialID, String aidName, String aidDateLine, int imageID) {
        /*String titleLast = title.getText().toString();
        String descriptionLast = description.getText().toString();

        Intent intent = new Intent();
        intent.putExtra("titleLast", titleLast);
        intent.putExtra("descriptionLast", descriptionLast);

        if (noteId != -1) {
            intent.putExtra("noteId", noteId);
            setResult(RESULT_OK, intent);
            finish();
        }
         */
        donationAmount -= minUserAmount * minUserSlots;
        availableSlot -= minUserSlots;

        Log.d("donationAmountFinal", String.valueOf(donationAmount));
        Log.d("availableSlotFinal", String.valueOf(availableSlot));

        Intent intent = new Intent(MainActivityPart5S4.this, MainActivityPart5S2.class);
        intent.putExtra("aidName", aidName);
        intent.putExtra("donationAmount", donationAmount);
        intent.putExtra("availableSlot", availableSlot);
        intent.putExtra("financialID", financialID);
        intent.putExtra("aidDateLine", aidDateLine);
        intent.putExtra("imageID", imageID);

        if (financialID != -1) {
            intent.putExtra("noteId", financialID);
            intent.putExtra("isUpdated", 1);
            setResult(RESULT_OK, intent);
            startActivity(intent);
        }

    }
}