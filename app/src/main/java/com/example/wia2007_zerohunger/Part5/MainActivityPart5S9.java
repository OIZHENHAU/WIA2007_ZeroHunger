package com.example.wia2007_zerohunger.Part5;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.Part5.reader.FinancialAdapter;
import com.example.wia2007_zerohunger.Part5.reader.FinancialData;
import com.example.wia2007_zerohunger.Part5.reader.TextFileFinancialReader;
import com.example.wia2007_zerohunger.R;

import java.util.List;

public class MainActivityPart5S9 extends AppCompatActivity {

    Button backButtonP5S9;
    List<FinancialData> financialDataList;

    private RecyclerView recyclerView;
    private FinancialAdapter financialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_part5_s9);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        generateFinancialDataList();
        Log.d("Financial List Size: ", String.valueOf(financialDataList.size()));

        backButtonP5S9 = findViewById(R.id.backButtonP5S9);

        backButtonP5S9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.locationListViewP5S9);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivityPart5S9.this));

        financialAdapter = new FinancialAdapter(financialDataList, MainActivityPart5S9.this);
        recyclerView.setAdapter(financialAdapter);
    }

    public void generateFinancialDataList() {
        TextFileFinancialReader textFileFinancialReader = new TextFileFinancialReader();
        financialDataList = textFileFinancialReader.readTextFile(this, R.raw.financial_aid);

    }
}