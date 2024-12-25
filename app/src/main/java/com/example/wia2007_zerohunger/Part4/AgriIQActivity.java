package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;


public class AgriIQActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agri_iqactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriIQ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button buttonEasy = findViewById(R.id.BtnGoEasy);
        Button buttonMedium = findViewById(R.id.btnGoMedium);
        Button buttonHard = findViewById(R.id.btnGoHard);
        Button viewMyProgress=findViewById(R.id.BtnViewProgress);
        viewMyProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgriIQActivity.this, ProgressActivity.class);
                startActivity(intent);
            }
        });
        buttonEasy.setOnClickListener(v -> startQuizActivity("Easy"));
        buttonMedium.setOnClickListener(v -> startQuizActivity("Medium"));
        buttonHard.setOnClickListener(v -> startQuizActivity("Hard"));
    }
    private void startQuizActivity(String difficulty) {
        Intent intent = new Intent(AgriIQActivity.this, QuizActivity.class);
        intent.putExtra("difficulty", difficulty); // 传递难度参数
        startActivity(intent);
    }
}