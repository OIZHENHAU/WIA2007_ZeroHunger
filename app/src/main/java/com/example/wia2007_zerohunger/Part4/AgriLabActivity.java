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
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;


public class AgriLabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agri_lab);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AgriLabMainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView tvGreeting = findViewById(R.id.TV2Greeting);
        String greetingMessage = TimeUtils.getGreetingMessage();
        tvGreeting.setText(greetingMessage);
        Button btnGoquiz = findViewById(R.id.BtnGoQuiz);
        btnGoquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgriLabActivity.this, AgriIQActivity.class);
                startActivity(intent);
            }
        });
        setupButtonLogic(R.id.BtnBeginner, "Beginner");
        setupButtonLogic(R.id.BtnIntermediate, "Intermediate");
        setupButtonLogic(R.id.BtnAdvanced, "Advanced");

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });




    }

    private void setupButtonLogic(int buttonId, String difficulty) {
        findViewById(buttonId).setOnClickListener(v -> {
            Intent intent = new Intent(AgriLabActivity.this, CourseListActivity.class);
            intent.putExtra("difficulty", difficulty);
            startActivity(intent);
        });
    }
}
