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
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ArrayList<Question> questions;
    private int currentQuestionIndex = 0; // 当前题目索引
    private int correctAnswers = 0; // 正确答案计数

    private TextView questionText;
    private RadioButton optionA, optionB, optionC, optionD;
    private RadioGroup optionsGroup;
    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new DatabaseHelper(this);

        questionText = findViewById(R.id.question_text);
        optionA = findViewById(R.id.option_a);
        optionB = findViewById(R.id.option_b);
        optionC = findViewById(R.id.option_c);
        optionD = findViewById(R.id.option_d);
        optionsGroup = findViewById(R.id.options_group);
        submitButton = findViewById(R.id.submit_button);

        String difficulty = getIntent().getStringExtra("difficulty");
        // 加载题目
        if (difficulty != null) {
            loadQuestions(difficulty); // 根据难度加载题目
        }

        // 显示第一题
        if (!questions.isEmpty()) {
            displayQuestion(currentQuestionIndex);
        }

        // 提交按钮点击事件
        submitButton.setOnClickListener(v -> {
            checkAnswer(); // 检查答案
            currentQuestionIndex++;

            if (currentQuestionIndex < questions.size()) {
                displayQuestion(currentQuestionIndex); // 显示下一题
            } else {
                // 所有题目完成时，显示弹窗
                showSubmissionSuccessDialog();
            }
        });
    }

    private void loadQuestions(String difficulty) {
        Cursor cursor = dbHelper.getQuestionsByDifficulty(difficulty);
        questions = new ArrayList<>();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int contentIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CONTENT);
                int optionAIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_OPTION_A);
                int optionBIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_OPTION_B);
                int optionCIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_OPTION_C);
                int optionDIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_OPTION_D);
                int correctAnswerIndex = cursor.getColumnIndex(DatabaseHelper.COLUMN_CORRECT_ANSWER);

                if (contentIndex >= 0 && optionAIndex >= 0 && optionBIndex >= 0 &&
                        optionCIndex >= 0 && optionDIndex >= 0 && correctAnswerIndex >= 0) {

                    String content = cursor.getString(contentIndex);
                    String optionA = cursor.getString(optionAIndex);
                    String optionB = cursor.getString(optionBIndex);
                    String optionC = cursor.getString(optionCIndex);
                    String optionD = cursor.getString(optionDIndex);
                    String correctAnswer = cursor.getString(correctAnswerIndex);

                    questions.add(new Question(content, optionA, optionB, optionC, optionD, correctAnswer));
                }
            } while (cursor.moveToNext());
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    private void displayQuestion(int index) {
        Question question = questions.get(index);
        questionText.setText(question.getContent());
        optionA.setText(question.getOptionA());
        optionB.setText(question.getOptionB());
        optionC.setText(question.getOptionC());
        optionD.setText(question.getOptionD());
        optionsGroup.clearCheck(); // 清除选择
    }

    private void checkAnswer() {
        int selectedId = optionsGroup.getCheckedRadioButtonId();
        RadioButton selectedOption = findViewById(selectedId);

        if (selectedOption != null && selectedOption.getText().equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
            correctAnswers++;
        }
        String difficulty = getIntent().getStringExtra("difficulty");
        dbHelper.updateProgress(difficulty, currentQuestionIndex + 1, correctAnswers);
    }

    private void showSubmissionSuccessDialog() {
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setTitle("Submission Successful");
        builder.setMessage("Your answers have been submitted successfully!");

        builder.setPositiveButton("View Result", (dialog, which) -> {
            Intent intent = new Intent(QuizActivity.this, ProgressActivity.class);
            startActivity(intent);
            finish();
        });

        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }
}