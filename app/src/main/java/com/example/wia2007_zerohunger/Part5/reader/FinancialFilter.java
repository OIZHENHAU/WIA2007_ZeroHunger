package com.example.wia2007_zerohunger.Part5.reader;

import java.util.ArrayList;
import java.util.List;

public class FinancialFilter {
    public List<FinancialData> getFinancialByCondition(List<FinancialData> financialDataList,
                                                       String name, int minAmount, int maxAmount, int minSlot, int maxSlot) {
        List<FinancialData> result = new ArrayList<>();

        for(FinancialData financialData : financialDataList) {
            String countryName1 = financialData.getLocality().split(",")[0].toLowerCase();
            String countryName2 = financialData.getLocality().split(",")[1].toLowerCase();

            int donationAmount = Integer.parseInt(financialData.getFips());
            int availableSlots = Integer.parseInt(financialData.getHouseholdsServed());

            String lowerCaseName = name.toLowerCase();

            if ((countryName1.contains(lowerCaseName) || countryName2.contains(lowerCaseName)) && (donationAmount >= minAmount && donationAmount <= maxAmount)
                    && (availableSlots >= minSlot && availableSlots <= maxSlot)) {
                result.add(financialData);
            }
        }

        return result;
    }
}
