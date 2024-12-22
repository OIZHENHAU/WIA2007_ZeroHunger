package com.example.wia2007_zerohunger.Part5.reader;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextFileFinancialReader {

    public List<FinancialData> readTextFile(Context context, int resourceId) {
        List<FinancialData> arrFinancialData = new ArrayList<>();
        Log.d("Resources ID for financial_aid: ", String.valueOf(resourceId));

        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split("\\s+");

                String year = values[0];
                String month = values[1];
                String fips = values[2];
                String locality = values[3];
                String householdsServed = values[4];
                String individualsServed = values[5];
                String poundsOfFoodDistributed = values[6];
                double lat = Double.parseDouble(values[7]);
                double lon = Double.parseDouble(values[8]);

                FinancialData financialData = new FinancialData(year, month, fips, locality, householdsServed, individualsServed, poundsOfFoodDistributed, lat, lon);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrFinancialData;
    }
}
