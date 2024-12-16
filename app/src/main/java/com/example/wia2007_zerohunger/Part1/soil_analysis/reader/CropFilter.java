package com.example.wia2007_zerohunger.Part1.soil_analysis.reader;

import com.example.wia2007_zerohunger.Part1.soil_analysis.CropData;

import java.util.ArrayList;
import java.util.List;

public class CropFilter {

    public List<String> getLabelForConditions(List<CropData> cropDataList, double N, double P, double K, double temperature, double humidity, double ph, double rainfall) {
        List<String> result = new ArrayList<>();

        for(CropData cropData : cropDataList) {
            if (cropData.getN() <= N && cropData.getP() <= P && cropData.getK() <= K && cropData.getTemperature() <= temperature && cropData.getHumidity() <= humidity && cropData.getPh() < ph && cropData.getRainfall() <= rainfall) {
                result.add(cropData.getLabel());
            }
        }
        return result;
    }
}
