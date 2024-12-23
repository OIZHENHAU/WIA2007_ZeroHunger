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
                //Log.d("Line", line);

                String year = values[0];
                //Log.d("Year", year);
                String month = values[1];
                //Log.d("Month", month);
                String fips = values[2];
                //Log.d("FIPS", fips);
                String locality = values[3];
                //Log.d("Locality", locality);
                String householdsServed = values[4];
                //Log.d("Households Served", householdsServed);
                String individualsServed = values[5];
                //Log.d("Individuals Served", individualsServed);
                String poundsOfFoodDistributed = values[6];
                //Log.d("Pounds of Food Distributed", poundsOfFoodDistributed);
                double lat = Double.parseDouble(values[7]);
                //Log.d("LAT", String.valueOf(lat));
                double lon = Double.parseDouble(values[8]);
                //Log.d("LON", String.valueOf(lon));

                FinancialData financialData = new FinancialData(year, month, fips, locality, householdsServed, individualsServed, poundsOfFoodDistributed, lat, lon);
                arrFinancialData.add(financialData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrFinancialData;
    }
}
