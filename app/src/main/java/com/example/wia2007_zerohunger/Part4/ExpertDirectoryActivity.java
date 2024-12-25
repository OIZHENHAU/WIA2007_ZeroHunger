package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.content.Intent;
import android.database.Cursor;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class ExpertDirectoryActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(this); // 初始化数据库帮助类
        setContentView(R.layout.activity_expert_directory);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriIQ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 初始化 RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerViewExperts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Log.d("ExpertDirectory", "RecyclerView initialized and layout manager set.");

        // 获取专家数据并设置适配器
        List<Expert> expertList = getExpertData();
        Log.d("ExpertDirectory", "Expert list size: " + expertList.size());
        ExpertAdapter adapter = new ExpertAdapter(expertList, this::onExpertClicked);
        recyclerView.setAdapter(adapter);
        Log.d("ExpertDirectory", "RecyclerView adapter set.");

    }

    private List<Expert> getExpertData() {
        List<Expert> expertList = new ArrayList<>();
        Cursor cursor = null;

        try {
            cursor = dbHelper.getAllExperts();
            Log.d("ExpertDirectory", "Query executed for all experts.");
            // 从数据库获取数据
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    expertList.add(new Expert(
                            cursor.getString(cursor.getColumnIndexOrThrow("name")),
                            cursor.getString(cursor.getColumnIndexOrThrow("specialization")),
                            cursor.getDouble(cursor.getColumnIndexOrThrow("rating")),
                            R.drawable.expertprofile // 使用固定头像资源
                    ));
                } while (cursor.moveToNext());
            }Log.d("ExpertDirectory", "Total experts: " + expertList.size());
            for (Expert expert : expertList) {
                Log.d("ExpertDirectory", "Expert: " + expert.getName() +
                        ", Specialization: " + expert.getSpecialization() +
                        ", Rating: " + expert.getRating());
            }
        } catch (Exception e) {
            Log.e("ExpertDirectory", "Error fetching experts from database.", e);// 打印错误日志
        } finally {
            if (cursor != null) cursor.close(); // 确保 Cursor 关闭，防止内存泄漏
        }

        return expertList;
    }


    // 点击专家时触发的事件
//
//    private void onExpertClicked(Expert expert) {
//        Log.d("ExpertDirectory", "Expert clicked: Name=" + expert.getName() +
//                ", Specialization=" + expert.getSpecialization() +
//                ", Rating=" + expert.getRating());
//
//        Intent intent = new Intent(this, ExpertDetailActivity.class);
//        intent.putExtra("expertName", expert.getName()); // 传递专家名字
//        startActivity(intent);
//    }
    private void onExpertClicked(Expert expert) {
        Log.d("ExpertDirectory", "Expert clicked: Name=" + expert.getName() +
                ", Specialization=" + expert.getSpecialization() +
                ", Rating=" + expert.getRating());

        // 跳转到 ExpertDetailActivity，并传递专家信息
        Intent intent = new Intent(this, ExpertDetailActivity.class);
        intent.putExtra("expertName", expert.getName());
        intent.putExtra("expertSpecialization", expert.getSpecialization());
        intent.putExtra("expertRating", expert.getRating());
        intent.putExtra("avatarResId", expert.getAvatarResId());
        startActivity(intent);
    }


}
