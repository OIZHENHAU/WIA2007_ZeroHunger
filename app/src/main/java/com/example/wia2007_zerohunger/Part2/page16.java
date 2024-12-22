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

public class page16 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_page16);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button page16_aboutUsBtnP2 = findViewById(R.id.page16_aboutUsBtnP2);
        Button page16_resourcesBtnP2 = findViewById(R.id.page16_resourcesBtnP2);
        Button page16_donationBtnP2 = findViewById(R.id.page16_donationBtnP2);
        Button page16_getHelpBtnP2 = findViewById(R.id.page16_getHelpBtnP2);
        Button page16_viewDetailsBtnP2 = findViewById(R.id.page16_viewDetailsBtnP2);

        /*page16_aboutUsBtnP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent();
                startActivity(intent);
            }
        });*/

        page16_resourcesBtnP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(page16.this, page18.class);
                startActivity(intent);
            }
        });

        page16_donationBtnP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent (page16.this, page19.class);
                startActivity(intent);
            }
        });

        page16_getHelpBtnP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(page16.this, page23.class);
                startActivity(intent);
            }
        });

        page16_viewDetailsBtnP2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(page16.this, page17.class);
                startActivity(intent);
            }
        });

    }
}