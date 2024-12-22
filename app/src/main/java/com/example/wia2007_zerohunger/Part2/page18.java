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

public class page18 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page18);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button page18_aboutUsBtnP2 = findViewById(R.id.page18_aboutUsBtnP2);
        Button page18_resourcesBtnP2 = findViewById(R.id.page18_resourcesBtnP2);
        Button page18_donationBtnP2 = findViewById(R.id.page18_donationBtnP2);
        Button page18_getHelpBtnP2 = findViewById(R.id.page18_getHelpBtnP2);

        page18_aboutUsBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page18.this, page16.class);
                startActivity(intent);
            }
        });

        /*page18_resourcesBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                startActivity(intent);
            }
        });*/

        page18_donationBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(page18.this, page19.class);
                startActivity(intent);
            }
        });

        page18_getHelpBtnP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(page18.this, page23.class);
                startActivity(intent);
            }
        });

    }
}