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

import com.example.wia2007_zerohunger.MainMenu;
import com.example.wia2007_zerohunger.R;

public class MainActivityPart5S1 extends AppCompatActivity {

    Button DFAP, FMT, FBSL, backHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        DFAP = findViewById(R.id.DFAP);
        FMT = findViewById(R.id.FMT);
        FBSL = findViewById(R.id.FBSL);
        backHomeButton = findViewById(R.id.backHomeButton);

        backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S1.this, MainMenu.class);
                startActivity(intent);
                finish();
            }
        });

        DFAP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S1.this, MainActivityPart5S2.class);
                startActivity(intent);
            }
        });

        FMT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S1.this, MainActivityPart5S5.class);
                startActivity(intent);
            }
        });

        FBSL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityPart5S1.this, MainActivityPart5S9a.class);
                startActivity(intent);
            }
        });

    }

}