package com.example.wia2007_zerohunger.Part2;

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

public class page19 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page19);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button page19_aboutUsBtnP2 = findViewById(R.id.page19_aboutUsBtnP2);
        Button page19_resourcesBtnP2 = findViewById(R.id.page19_resourcesBtnP2);
        Button page19_donationBtnP2 = findViewById(R.id.page19_donationBtnP2);
        Button page19_getHelpBtnP2 = findViewById(R.id.page19_getHelpBtnP2);
        Button page19_registerBtnP2 = findViewById(R.id.page19_registerBtnP2);
        Button page19_readMoreBtnP2 = findViewById(R.id.page19_readMoreBtnP2);

        page19_aboutUsBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page19.this, page16.class);
                startActivity(intent);
            }
        });

        page19_resourcesBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    Intent intent = new Intent(page19.this, page18.class);
                    startActivity(intent);
            }
        });

        /*page19_donationBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivity(intent);
            }
        });*/

        page19_getHelpBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page19.this, page23.class);
                startActivity(intent);
            }
        });

        page19_registerBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (page19.this, page21.class);
                startActivity(intent);
            }
        });

        page19_readMoreBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (page19.this, page20.class);
                startActivity(intent);
            }
        });

    }
}