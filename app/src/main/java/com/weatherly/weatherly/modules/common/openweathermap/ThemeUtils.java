package com.weatherly.weatherly.modules.common.openweathermap;

import android.app.Activity;

import com.weatherly.weatherly.R;

import java.util.Calendar;

public class ThemeUtils {
    public static void setAppTheme(Activity activity) {
        Calendar rightNow = Calendar.getInstance();
        int currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
        String hour = (currentHour > 4 && currentHour < 18) ? "day" : "night";

        if (hour.equals("day"))
            activity.setTheme(R.style.AppTheme_Day);
        else
            activity.setTheme(R.style.AppTheme_Night);
    }
}
