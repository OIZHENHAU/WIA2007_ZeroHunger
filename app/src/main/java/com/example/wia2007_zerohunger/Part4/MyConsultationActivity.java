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
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyConsultationActivity extends AppCompatActivity {
    private ConsultationAdapter adapter;
    private RecyclerView rvConsultations;
    private List<Consultation> consultationList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_consultation);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriIQ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        rvConsultations = findViewById(R.id.rvConsultations);

        // 加载假数据
        loadAppointmentData();




        // 设置RecyclerView
        adapter = new ConsultationAdapter(consultationList, this::onConsultationClicked);
        rvConsultations.setLayoutManager(new LinearLayoutManager(this));
        rvConsultations.setAdapter(adapter);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadAppointmentData();
        adapter.notifyDataSetChanged();
    }

    //
    private void loadAppointmentData() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        consultationList.clear(); // 清空旧数据

        Cursor cursor = dbHelper.getAppointmentsWithExpertNames(); // 调用 JOIN 查询

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String expertName = cursor.getString(cursor.getColumnIndexOrThrow("expert_name")); // 正确的列名
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));
                String timeSlot = cursor.getString(cursor.getColumnIndexOrThrow("time_slot"));
                String status = cursor.getString(cursor.getColumnIndexOrThrow("status"));
                Log.d("MyConsultationActivity", "Loaded Appointment: ID=" + id +
                        ", Expert=" + expertName + ", Date=" + date + ", TimeSlot=" + timeSlot + ", Status=" + status);

                consultationList.add(new Consultation(id,expertName, date, timeSlot, status));
            } while (cursor.moveToNext());
            cursor.close();
        } else {
            Log.d("MyConsultationActivity", "No appointments found");
        }
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation c = consultationList.get(i);
            Log.d("MyConsultationActivity", "Data at index " + i + ": " +
                    "ID=" + c.getId() + ", Expert=" + c.getExpertName() + ", Date=" + c.getDate());
        }

    }




    private void onConsultationClicked(Consultation consultation) {
        Intent intent = new Intent(this, ConsultationDetailsActivity.class);
        intent.putExtra("consultation", consultation);
        startActivity(intent);
    }
}