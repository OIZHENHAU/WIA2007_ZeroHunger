package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;
import android.content.Intent;
import android.net.Uri;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class CourseListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_course_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CourseListPage), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String difficulty = getIntent().getStringExtra("difficulty");
        List<Course> courses = getCoursesByDifficulty(difficulty);

        RecyclerView recyclerView = findViewById(R.id.rvCourses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CourseAdapter adapter = new CourseAdapter(courses, this::onCourseClicked);
        recyclerView.setAdapter(adapter);
    }

    private List<Course> getCoursesByDifficulty(String difficulty) {
        DatabaseHelper db = new DatabaseHelper(this);
        return db.getCoursesByDifficulty(difficulty);
    }

    private void onCourseClicked(Course course) {
        // 使用外部浏览器/YouTube App打开链接
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(course.getUrl()));
        startActivity(intent);
    }
}
