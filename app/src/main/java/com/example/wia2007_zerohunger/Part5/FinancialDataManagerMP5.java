package com.example.wia2007_zerohunger.Part5;

import java.util.HashMap;

public class FinancialDataManagerMP5 {
    public static HashMap<String, FinancialDataMP5> financialDataMap = new HashMap<>();

    static {
        // Dummy data for 2 months
        financialDataMap.put("11/2024", new FinancialDataMP5(
                "11/2024", "November 2024", 350, 200, 125, 25, 0, 0, 0,
                650, 400, 0, 250, 0, 0, 0, 300
        ));

        financialDataMap.put("10/2024", new FinancialDataMP5(
                "10/2024", "October 2024", 400, 300, 50, 50, 0, 0, 0,
                700, 500, 0, 200, 0, 0, 0, 300
        ));
    }
}