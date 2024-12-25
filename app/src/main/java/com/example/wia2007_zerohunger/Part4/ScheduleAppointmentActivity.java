package com.example.wia2007_zerohunger.Part4;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.wia2007_zerohunger.R;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class ScheduleAppointmentActivity extends AppCompatActivity {
    private final HashMap<String, Boolean[]> bookingStatus = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule_appointment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CLAgriIQ), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TableLayout tableSchedule = findViewById(R.id.tableSchedule);
        Button btnBook = findViewById(R.id.btnBook);

        // 初始化预约状态
        initializeBookingStatus();

        // 动态生成时间表
        generateSchedule(tableSchedule);

        // 预约按钮的全局功能（可用于其他逻辑）
        btnBook.setOnClickListener(v -> {
            Toast.makeText(this, "Select a slot to book!", Toast.LENGTH_SHORT).show();
        });
    }

    private void initializeBookingStatus() {
        String[] timeSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM", "4:00 PM"};
        for (String time : timeSlots) {
            // 初始化每个时间段的预约状态为false（未预约）
            bookingStatus.put(time, new Boolean[]{false, false, false, false, false});
        }
    }

    private void generateSchedule(TableLayout tableLayout) {
        // 表头行
        TableRow headerRow = new TableRow(this);
        addTextToRow(headerRow, "Time Slot", true);
        addTextToRow(headerRow, "Monday", true);
        addTextToRow(headerRow, "Tuesday", true);
        addTextToRow(headerRow, "Wednesday", true);
        addTextToRow(headerRow, "Thursday", true);
        addTextToRow(headerRow, "Friday", true);
        tableLayout.addView(headerRow);

        // 时间段
        String[] timeSlots = {"9:00 AM", "10:00 AM", "11:00 AM", "2:00 PM", "3:00 PM", "4:00 PM"};

        for (String time : timeSlots) {
            TableRow row = new TableRow(this);

            // 时间段列
            addTextToRow(row, time, false);

            // 每天的预约状态
            for (int i = 0; i < 5; i++) {
                Button slotButton = new Button(this);
                slotButton.setText("Book");

                // 检查预约状态
                if (bookingStatus.get(time)[i]) {
                    slotButton.setText("Booked");
                    slotButton.setEnabled(false);
                }

                int dayIndex = i; // 为匿名内部类创建独立的变量
                slotButton.setOnClickListener(v -> {
                    if (!bookingStatus.get(time)[dayIndex]) {
                        // 显示确认对话框
                        showConfirmationDialog(time, dayIndex, slotButton);
                    } else {
                        Toast.makeText(this, "Already booked!", Toast.LENGTH_SHORT).show();
                    }
                });

                row.addView(slotButton);
            }

            tableLayout.addView(row);
        }
    }

    private void addTextToRow(TableRow row, String text, boolean isHeader) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextSize(isHeader ? 18 : 16);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        row.addView(textView);
    }

    //    private void showConfirmationDialog(String time, int dayIndex, Button slotButton) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Confirm Appointment");
//        builder.setMessage("Are you sure you want to book this slot: " + time + " on " + getDayName(dayIndex) + "?");
//
//        builder.setPositiveButton("Yes", (dialog, which) -> {
//            // 更新预约状态
//            bookingStatus.get(time)[dayIndex] = true;
//
//            // 更新按钮状态
//            slotButton.setText("Booked");
//            slotButton.setEnabled(false);
//
//            // 显示成功信息并弹出第二个确认弹窗
//            showSuccessDialog();
//        });
//
//        builder.setNegativeButton("No", null);
//        builder.show();
//    }
//private void showConfirmationDialog(String time, int dayIndex, Button slotButton) {
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    builder.setTitle("Confirm Appointment");
//    builder.setMessage("Are you sure you want to book this slot: " + time + " on " + getDayName(dayIndex) + "?");
//
//    builder.setPositiveButton("Yes", (dialog, which) -> {
//        // 假设 expertId 固定为 1（以后可以动态传入）
//        int expertId = 1;
//        String date = getDayName(dayIndex); // 星期几作为日期
//        String timeSlot = time;
//
//        // 插入预约记录到数据库
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dbHelper.insertAppointment(expertId, date, timeSlot, "Scheduled");
//
//        // 更新 UI 状态
//        bookingStatus.get(time)[dayIndex] = true;
//        slotButton.setText("Booked");
//        slotButton.setEnabled(false);
//
//        // 显示成功消息
//        showSuccessDialog();
//    });
//
//    builder.setNegativeButton("No", null);
//    builder.show();
//}
//private void showConfirmationDialog(String time, int dayIndex, Button slotButton) {
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//    builder.setTitle("Confirm Appointment");
//    builder.setMessage("Are you sure you want to book this slot: " + time + " on " + getDayName(dayIndex) + "?");
//
//    // 获取传递的 expertId
//    int expertId = getIntent().getIntExtra("expert_id", -1); // -1 表示未找到的默认值
//
//    if (expertId == -1) {
//        Toast.makeText(this, "Error: Expert ID not found!", Toast.LENGTH_SHORT).show();
//        return;
//    }
//
//    builder.setPositiveButton("Yes", (dialog, which) -> {
//        String date = getDayName(dayIndex); // 星期几作为日期
//        String timeSlot = time;
//
//        // 插入预约记录到数据库
//        DatabaseHelper dbHelper = new DatabaseHelper(this);
//        dbHelper.insertAppointment(expertId, date, timeSlot, "Scheduled");
//
//        // 更新 UI 状态
//        bookingStatus.get(time)[dayIndex] = true;
//        slotButton.setText("Booked");
//        slotButton.setEnabled(false);
//
//        // 显示成功消息
//        showSuccessDialog();
//    });
//
//    builder.setNegativeButton("No", null);
//    builder.show();
//}
    private void showConfirmationDialog(String time, int dayIndex, Button slotButton) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Appointment");
        builder.setMessage("Are you sure you want to book this slot: " + time + " on " + getDayName(dayIndex) + "?");

        // 获取传递的 expertName
        String expertName = getIntent().getStringExtra("expertName");

        // 查询 expertId
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        int expertId = dbHelper.getExpertIdByName(expertName);

        if (expertId == -1) {
            Toast.makeText(this, "Error: Expert ID not found!", Toast.LENGTH_SHORT).show();
            return;
        }

        builder.setPositiveButton("Yes", (dialog, which) -> {
            String date = getDayName(dayIndex); // 星期几作为日期
            String timeSlot = time;

            // 插入预约记录到数据库
            dbHelper.insertAppointment(expertId, date, timeSlot, "Scheduled");

            // 更新 UI 状态
            bookingStatus.get(time)[dayIndex] = true;
            slotButton.setText("Booked");
            slotButton.setEnabled(false);

            // 显示成功消息
            showSuccessDialog();
        });

        builder.setNegativeButton("No", null);
        builder.show();
    }


    private void showSuccessDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Booking Confirmed");
        builder.setMessage("Your booking has been successfully confirmed!");

        // 添加跳转到 My Consultation 的按钮
        builder.setPositiveButton("My Consultation", (dialog, which) -> {
            // 跳转到 MyConsultationActivity
            Intent intent = new Intent(ScheduleAppointmentActivity.this, MyConsultationActivity.class);
            startActivity(intent);
        });

        // 添加关闭按钮
        builder.setNegativeButton("Close", (dialog, which) -> dialog.dismiss());

        builder.show();
    }


    private String getDayName(int dayIndex) {
        switch (dayIndex) {
            case 0:
                return "Monday";
            case 1:
                return "Tuesday";
            case 2:
                return "Wednesday";
            case 3:
                return "Thursday";
            case 4:
                return "Friday";
            default:
                return "";
        }
    }

}