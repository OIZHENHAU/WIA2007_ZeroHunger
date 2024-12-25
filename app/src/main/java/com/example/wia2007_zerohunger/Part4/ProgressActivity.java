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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;


public class ProgressActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;

    private TextView easyProgressText, mediumProgressText, hardProgressText;
    private Button resetEasyButton, resetMediumButton, resetHardButton;
    private Button specialAwardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 启用系统默认返回图标
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        dbHelper = new DatabaseHelper(this);

        // 初始化视图
        easyProgressText = findViewById(R.id.easy_progress_text);
        mediumProgressText = findViewById(R.id.medium_progress_text);
        hardProgressText = findViewById(R.id.hard_progress_text);
        resetEasyButton = findViewById(R.id.reset_easy_button);
        resetMediumButton = findViewById(R.id.reset_medium_button);
        resetHardButton = findViewById(R.id.reset_hard_button);
        //找到special award button
        specialAwardButton = findViewById(R.id.special_award_button);
        // 加载进度
        loadProgress("Easy", easyProgressText);
        loadProgress("Medium", mediumProgressText);
        loadProgress("Hard", hardProgressText);


// 重置 Easy 进度
        resetEasyButton.setOnClickListener(v -> {
            dbHelper.resetProgress("Easy");
            loadProgress("Easy", easyProgressText);
        });

        // 重置 Medium 进度
        resetMediumButton.setOnClickListener(v -> {
            dbHelper.resetProgress("Medium");
            loadProgress("Medium", mediumProgressText);
        });

        // 重置 Hard 进度
        resetHardButton.setOnClickListener(v -> {
            dbHelper.resetProgress("Hard");
            loadProgress("Hard", hardProgressText);
        });
        specialAwardButton.setOnClickListener(v -> {
            // 跳转至Special Award界面（此处SpecialAwardActivity为你已创建的Activity类名）
            Intent intent = new Intent(ProgressActivity.this, SpecialAwardActivity.class);
            startActivity(intent);
        });
    }
    private void loadProgress(String difficulty, TextView progressText) {
        Cursor cursor = dbHelper.getProgress(difficulty);
        int questionsCompleted = 0;
        int correctAnswers = 0;

        if (cursor != null && cursor.moveToFirst()) {
            try {
                int questionsCompletedIndex = cursor.getColumnIndex("questions_completed");
                int correctAnswersIndex = cursor.getColumnIndex("correct_answers");

                if (questionsCompletedIndex != -1) {
                    questionsCompleted = cursor.getInt(questionsCompletedIndex);
                }
                if (correctAnswersIndex != -1) {
                    correctAnswers = cursor.getInt(correctAnswersIndex);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }

        int accuracyRate = (questionsCompleted == 0) ? 0 : (correctAnswers * 100 / questionsCompleted);
        String progressReport = "Questions Completed: " + questionsCompleted + "/20\n" +
                "Correct Answers: " + correctAnswers + "\n" +
                "Accuracy Rate: " + accuracyRate + "%";
        progressText.setText(progressReport);
        if ("Hard".equals(difficulty)) {
            if (questionsCompleted == 20 && accuracyRate == 100) {
                // 显示special award按钮
                specialAwardButton.setVisibility(View.VISIBLE);
            } else {
                specialAwardButton.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // 返回上一级界面
        return true;
    }


}