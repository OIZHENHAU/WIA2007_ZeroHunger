package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.widget.Button;


public class SpecialAwardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_special_award);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btnAccept=findViewById(R.id.BtnAccept);
        btnAccept.setOnClickListener(v -> {
            Intent intent = new Intent(SpecialAwardActivity.this, AgriIQActivity.class);
            startActivity(intent);
            finish(); // 可选：如果不想在返回键时再回到这个Award页面的话，可以调用finish()结束当前Activity
        });
    }
}