package com.example.wia2007_zerohunger.Part1.soil_analysis.reader;

import android.content.Context;

import com.example.wia2007_zerohunger.Part1.soil_analysis.CropData;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextFileCropReader {
    public List<CropData> readTextFile(Context context, int resourceId) {
        List<CropData> cropDataList = new ArrayList<>();

        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(" ");
                int N = Integer.parseInt(values[0]);
                int P = Integer.parseInt(values[1]);
                int K = Integer.parseInt(values[2]);
                double temperature = Double.parseDouble(values[3]);
                double humidity = Double.parseDouble(values[4]);
                double ph = Double.parseDouble(values[5]);
                double rainfall = Double.parseDouble(values[6]);
                String label = values[7];

                CropData cropData = new CropData(N, P, K, temperature, humidity, ph, rainfall, label);
                cropDataList.add(cropData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return cropDataList;
    }
}
