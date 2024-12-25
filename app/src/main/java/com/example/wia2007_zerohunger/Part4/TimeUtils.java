package com.example.wia2007_zerohunger.Part4;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtils {
    public static String getGreetingMessage() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if (hour >= 5 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 18) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}

