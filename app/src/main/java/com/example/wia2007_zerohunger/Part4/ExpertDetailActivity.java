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
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


public class ExpertDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_expert_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriIQ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // 获取传递的数据
        String name = getIntent().getStringExtra("expertName");
        String specialization = getIntent().getStringExtra("expertSpecialization");
        double rating = getIntent().getDoubleExtra("expertRating", 0.0);
        int avatarResId = getIntent().getIntExtra("avatarResId", 0);

        // 设置数据到界面
        ImageView ivAvatar = findViewById(R.id.ivAvatar);
        TextView tvName = findViewById(R.id.tvName);
        TextView tvSpecialization = findViewById(R.id.tvSpecialization);
        RatingBar ratingBar = findViewById(R.id.ratingBar);

        ivAvatar.setImageResource(avatarResId);
        tvName.setText("Name: " + name);
        tvSpecialization.setText("Specialization: " + specialization);
        ratingBar.setRating((float) rating);
//        Button btnBook = findViewById(R.id.BtnBook);
//        btnBook.setOnClickListener(v -> {
//            Intent intent = new Intent(ExpertDetailActivity.this, ScheduleAppointmentActivity.class);
//            startActivity(intent);
//        });
//        Button btnBook = findViewById(R.id.BtnBook);
//        btnBook.setOnClickListener(v -> {
//            DatabaseHelper dbHelper = new DatabaseHelper(this);
//            int expertId = dbHelper.getExpertIdByName(name); // 根据专家名字获取 ID
//
//            if (expertId != -1) {
//                Intent intent = new Intent(ExpertDetailActivity.this, ScheduleAppointmentActivity.class);
//                intent.putExtra("expert_id", expertId); // 传递专家 ID
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "Error: Expert not found!", Toast.LENGTH_SHORT).show();
//            }
//        });
        Button btnBook = findViewById(R.id.BtnBook);
        btnBook.setOnClickListener(v -> {
            // 跳转到预约界面，并传递专家信息
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            int expertId = dbHelper.getExpertIdByName(name); // 根据名字获取专家ID

            if (expertId != -1) {
                Intent intent = new Intent(ExpertDetailActivity.this, ScheduleAppointmentActivity.class);
                intent.putExtra("expert_id", expertId); // 传递专家ID
                intent.putExtra("expertName", name);    // 传递专家名字（可选）
                startActivity(intent);
            } else {
                Toast.makeText(this, "Error: Expert not found!", Toast.LENGTH_SHORT).show();
            }
        });


    }
}