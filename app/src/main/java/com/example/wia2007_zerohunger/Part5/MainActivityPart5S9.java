package com.example.wia2007_zerohunger.Part5;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.Part5.reader.FinancialAdapter;
import com.example.wia2007_zerohunger.Part5.reader.FinancialData;
import com.example.wia2007_zerohunger.Part5.reader.FinancialFilter;
import com.example.wia2007_zerohunger.Part5.reader.TextFileFinancialReader;
import com.example.wia2007_zerohunger.R;

import java.util.List;

public class MainActivityPart5S9 extends AppCompatActivity {

    Button backButtonP5S9;
    List<FinancialData> financialDataList;

    List<FinancialData> financialResultList;
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

        Intent intent = getIntent();
        int filterValue = intent.getIntExtra("filter", 0);
        Log.d("filterValue",String.valueOf(filterValue));

        if (filterValue == 1) {
            FinancialFilter filter = new FinancialFilter();
            String countryName = intent.getStringExtra("countryName");
            int minAmount = intent.getIntExtra("minAmount", 0);
            int maxAmount = intent.getIntExtra("maxAmount", 0);
            int minSlots = intent.getIntExtra("minSlots", 0);
            int maxSlots = intent.getIntExtra("maxSlots", 0);
            Log.d("countryNameP5S9", countryName);
            Log.d("minAmountP5S9", String.valueOf(minAmount));
            Log.d("maxAmountP5S9", String.valueOf(maxAmount));
            Log.d("minSlotsP5S9", String.valueOf(minSlots));
            Log.d("maxSlotsP5S9", String.valueOf(maxSlots));

            financialResultList = filter.getFinancialByCondition(financialDataList, countryName, minAmount, maxAmount, minSlots, maxSlots);

        } else {
            financialResultList = financialDataList;
        }

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

        financialAdapter = new FinancialAdapter(financialResultList, MainActivityPart5S9.this);
        recyclerView.setAdapter(financialAdapter);

        //Check for Financial Aid Location
        financialAdapter.setOnItemClickListener(new FinancialAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(FinancialData financialData) {
                Intent intent = new Intent(MainActivityPart5S9.this, MainActivityPart5S10.class);
                /*intent.putExtra("id", note.getId());
                intent.putExtra("title", note.getTitle());
                intent.putExtra("description", note.getDescription());
                 */

                intent.putExtra("financialLatitude", financialData.getLat());
                intent.putExtra("financialLongitude", financialData.getLon());

                startActivity(intent);

            }
        });
    }

    public void generateFinancialDataList() {
        TextFileFinancialReader textFileFinancialReader = new TextFileFinancialReader();
        financialDataList = textFileFinancialReader.readTextFile(this, R.raw.financial_aid);

    }
}