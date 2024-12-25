package com.example.wia2007_zerohunger.Part4;

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


public class AgriAidActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agri_aid);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriAid), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findViewById(R.id.BtnExpertDirectory).setOnClickListener(v -> {
            Intent intent = new Intent(AgriAidActivity.this, ExpertDirectoryActivity.class);
            startActivity(intent);
        });
        Button btnMyConsultation=findViewById(R.id.BtnMyConsultation);
        btnMyConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgriAidActivity.this, MyConsultationActivity.class);
                startActivity(intent);
            }
        });
        Button btnMakeSchedule=findViewById(R.id.BtnSchedule);
        btnMakeSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgriAidActivity.this, ExpertDirectoryActivity.class);
                startActivity(intent);
            }
        });
    }
}