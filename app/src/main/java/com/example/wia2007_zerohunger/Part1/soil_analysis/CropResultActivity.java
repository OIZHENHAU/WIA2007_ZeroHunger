package com.example.wia2007_zerohunger.Part1.soil_analysis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.R;
import com.example.wia2007_zerohunger.databinding.ActivityCropResultBinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CropResultActivity extends AppCompatActivity {

    ActivityCropResultBinding cropResultBinding;
    private RecyclerView recyclerView;
    private ArrayList<CropResult> cropResultList = new ArrayList<CropResult>();
    private ArrayList<String> cropLabelList = new ArrayList<String>();
    private ArrayList<String> plantingTimeList = new ArrayList<String>();
    private ArrayList<String> managementTipsList = new ArrayList<String>();
    private CropResultAdapter cropResultAdapter;

    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        cropResultBinding = ActivityCropResultBinding.inflate(getLayoutInflater());
        setContentView(cropResultBinding.getRoot());

        cropResultBinding.cropResultP1Toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        Intent intent = getIntent();
        ArrayList<String> cropDataList = intent.getStringArrayListExtra("cropDataList");
        Log.d("Crop Data In Crop Result", String.valueOf(cropDataList.size()));

        Set<String> setWithoutDuplicates = new HashSet<>(cropDataList);
        ArrayList<String> cropDataListWithoutDuplicates = new ArrayList<>(setWithoutDuplicates);

        generateCropLabelList();
        generatePlantingTimeList();
        generateManagementTipsList();


        recyclerView = findViewById(R.id.cropResultRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CropResultActivity.this));

        generateCropResultList(cropDataListWithoutDuplicates);

        Log.d("Array Crop Result", String.valueOf(cropResultList.size()));

        if (cropResultList.size() == 0) {
            Toast.makeText(CropResultActivity.this, "No crop recommendation found", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(CropResultActivity.this, "Crop recommendation found", Toast.LENGTH_SHORT).show();
        }

        cropResultAdapter = new CropResultAdapter(cropResultList, CropResultActivity.this);
        recyclerView.setAdapter(cropResultAdapter);

    }

    //Need to Change
    public void generateCropResultList(ArrayList<String> cropDataList) {
        int arr_length = cropDataList.size();

        for(int i = 0; i < arr_length; i++) {
            int randomCropLabelIndex = random.nextInt(cropLabelList.size());
            int randomPlantingTimeIndex = random.nextInt(plantingTimeList.size());
            int randomManagementTipsIndex = random.nextInt(managementTipsList.size());

            String randomCropLabel = cropDataList.get(i);
            String randomPlantingTime = plantingTimeList.get(randomPlantingTimeIndex);
            String randomManagementTips = managementTipsList.get(randomManagementTipsIndex);

            CropResult currentCropResult = new CropResult(randomCropLabel, randomPlantingTime, randomManagementTips);
            cropResultList.add(currentCropResult);
        }
    }

    public void generateCropLabelList() {
        cropLabelList.add("rice");
        cropLabelList.add("maize");
        cropLabelList.add("chickpea");
        cropLabelList.add("kidneybeans");
        cropLabelList.add("pigeonpeas");
        cropLabelList.add("mothbeans");
        cropLabelList.add("mungbean");
        cropLabelList.add("blackgram");
        cropLabelList.add("lentil");
        cropLabelList.add("pomegranate");
        cropLabelList.add("banana");
        cropLabelList.add("mango");
        cropLabelList.add("grapes");
        cropLabelList.add("watermelon");
        cropLabelList.add("muskmelon");
        cropLabelList.add("apple");
        cropLabelList.add("orange");
        cropLabelList.add("papaya");
        cropLabelList.add("coconut");
        cropLabelList.add("cotton");
        cropLabelList.add("jute");
        cropLabelList.add("coffee");

    }

    public void generatePlantingTimeList() {
        plantingTimeList.add("Spring (March-May)");
        plantingTimeList.add("Early Summer (June)");
        plantingTimeList.add("Mid Summer (July)");
        plantingTimeList.add("Late Summer (August)");
        plantingTimeList.add("Early Fall (September)");
        plantingTimeList.add("Mid Fall (October)");
        plantingTimeList.add("Late Fall (November)");
        plantingTimeList.add("Winter (December-February)");
        plantingTimeList.add("Indoor Planting (Year-round)");
        plantingTimeList.add("Container Gardening (Year-round)");

    }

    public void generateManagementTipsList() {
        managementTipsList.add("Test your soil and amend it with compost or organic matter");
        managementTipsList.add("Water plants deeply and infrequently to encourage deep root growth.");
        managementTipsList.add("Apply mulch around plants to conserve moisture,and maintain soil temperature.");
        managementTipsList.add("Introduce companion planting to deter pests.");
        managementTipsList.add("Regularly prune plants to remove diseased branches and improve air circulation.");
        managementTipsList.add("Use organic fertilizers or compost tea to provide essential nutrients to plants.");
        managementTipsList.add("Rotate crops each year to prevent soil-borne diseases");
        managementTipsList.add("Remove weeds regularly to reduce competition for nutrients");
        managementTipsList.add("Harvest crops at the right time to ensure peak flavor and nutrition.");
        managementTipsList.add("Keep a gardening journal to track planting dates and crop performance.");
    }
}