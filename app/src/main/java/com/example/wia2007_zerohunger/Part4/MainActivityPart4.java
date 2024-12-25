package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivityPart4 extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tvProgress;
    private Handler handler = new Handler();
    private TextView tvWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part4);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.AgriLabMainPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        TextView tvGreeting = findViewById(R.id.TVGreeting);
        String greetingMessage = getGreetingMessage();
        tvGreeting.setText(greetingMessage);
        progressBar=findViewById(R.id.progressBar);
        tvProgress=findViewById(R.id.TVProgress);
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(0);

        tvWeather=findViewById(R.id.TVWeather);
        String weatherInfo=getWeatherInfo();
        tvWeather.setText(weatherInfo);
        Button btnAgrilab=findViewById(R.id.BTNAgriLab);
        Button btnAriAid=findViewById(R.id.BTNAgriAid);
        Button btnAgriIQ=findViewById(R.id.BTNAgriIQ);
        btnAgriIQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivityPart4.this, AgriIQActivity.class);
                startActivity(intent);
            }
        });
        btnAriAid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityPart4.this, AgriAidActivity.class);
                startActivity(intent);
            }
        });
        btnAgrilab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivityPart4.this, AgriLabActivity.class);
                startActivity(intent);
            }
        });
        //在这里是题库的加载逻辑
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        if (!dbHelper.isDatabaseInitialized()) {
            dbHelper.insertAllQuestions(); // 插入所有题目
            Toast.makeText(this, "Questions initialized!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Questions already initialized.", Toast.LENGTH_SHORT).show();
        }
        //初始化进度
        dbHelper.initializeProgress("Easy");
        dbHelper.initializeProgress("Medium");
        dbHelper.initializeProgress("Hard");
        if (!dbHelper.isDatabaseInitialized("Experts")) { // 检查是否初始化了专家表
            dbHelper.insertInitialExperts(dbHelper.getWritableDatabase());
            Toast.makeText(this, "Expert data initialized!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Experts already initialized.", Toast.LENGTH_SHORT).show();
        }
        int easyCompleted = getQuestionsCompleted("Easy");
        int mediumCompleted = getQuestionsCompleted("Medium");
        int hardCompleted = getQuestionsCompleted("Hard");

        int totalCompleted = easyCompleted + mediumCompleted + hardCompleted;
        int totalQuestions = 60; // Easy(20) + Medium(20) + Hard(20) = 60
        int overallCompletionPercentage = (int)((totalCompleted * 100.0f) / totalQuestions);

// 设置进度条与文本
        progressBar.setIndeterminate(false);
        progressBar.setMax(100);
        progressBar.setProgress(overallCompletionPercentage);
        tvProgress.setText("You have learned " + overallCompletionPercentage + "%");
        simulateCircularProgress(progressBar,overallCompletionPercentage);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // 关闭当前 Activity，返回上一个页面
        return true;
    }

    // 根据当前时间段返回不同的问候语
    private String getGreetingMessage() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("Current hour: " + hour);
        if (hour >= 5 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Good Afternoon";
        } else {
            // 包括晚上和凌晨
            return "Good Evening";
        }
    }
    //这部分需要从我们的database里面读取答题的进度报告
    private int getQuestionsCompleted(String difficulty) {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int questionsCompleted = 0;
        Cursor cursor = dbHelper.getProgress(difficulty);
        if (cursor != null && cursor.moveToFirst()) {
            int index = cursor.getColumnIndex("questions_completed");
            if (index != -1) {
                questionsCompleted = cursor.getInt(index);
            }
            cursor.close();
        }
        return questionsCompleted;
    }

    //    private void simulateCircularProgress(ProgressBar progressBar) {
//        int[] progress = {0}; // 使用数组存储进度值以支持匿名类
//        Handler handler = new Handler();
//
//        Runnable progressRunnable = new Runnable() {
//            @Override
//            public void run() {
//                if (progress[0] <= 100) {
//                    progressBar.setProgress(progress[0]); // 更新进度条
//                    tvProgress.setText("You have learned " + progress[0] + "%"); // 同步更新TextView
//                    progress[0] += 10; // 每次增加 10
//                    handler.postDelayed(this, 500); // 每 500 毫秒更新一次
//                } else {
//                    handler.removeCallbacks(this); // 停止更新
//                    tvProgress.setText("You have learned 100%");
//                }
//            }
//        };
//
//        handler.post(progressRunnable); // 开始更新
//    }
    private void simulateCircularProgress(ProgressBar progressBar, int targetProgress) {
        int[] progress = {0};
        Handler handler = new Handler();

        Runnable progressRunnable = new Runnable() {
            @Override
            public void run() {
                if (progress[0] <= targetProgress) {
                    progressBar.setProgress(progress[0]);
                    tvProgress.setText("You have learned " + progress[0] + "%");
                    progress[0]++;
                    handler.postDelayed(this, 20); // 每20毫秒更新1%
                } else {
                    handler.removeCallbacks(this);
                }
            }
        };
        handler.post(progressRunnable);
    }

    //这部分需要我们从API中读取一个天气的数据，这个逻辑需要实现
    private String getWeatherInfo() {
        // 模拟返回一个固定的温度值（例如32°C）
        String mockTemperature = "32°C";

        // 将来从API获取天气信息的逻辑
        // 示例：
        // String apiResponse = callWeatherAPI();
        // String temperature = parseTemperature(apiResponse);

        // 返回温度信息（暂时为模拟值）
        return "Current Temperature: " + mockTemperature;
    }


}