package com.weatherly.weatherly.modules.common.openweathermap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.weatherly.weatherly.R;

public class IconUtils {
    public static Drawable getWeatherIcon(Context context, String code) {
        switch (code) {
            case "04d":
                return ContextCompat.getDrawable(context, R.drawable.cloudyn);

            case "04n":
                return ContextCompat.getDrawable(context, R.drawable.cloudyn);

            case "01d":
                return ContextCompat.getDrawable(context, R.drawable.cleard);

            case "01n":
                return ContextCompat.getDrawable(context, R.drawable.clearn);

            case "02d":
                return ContextCompat.getDrawable(context, R.drawable.fewcloudd);

            case "02n":
                return ContextCompat.getDrawable(context, R.drawable.fewcloudn);

            case "03d":
                return ContextCompat.getDrawable(context, R.drawable.scatteredd);

            case "03n":
                return ContextCompat.getDrawable(context, R.drawable.scatteredd);

            case "09d":
                return ContextCompat.getDrawable(context, R.drawable.showerd);

            case "09n":
                return ContextCompat.getDrawable(context, R.drawable.showerd);

            case "10d":
                return ContextCompat.getDrawable(context, R.drawable.raind);

            case "10n":
                return ContextCompat.getDrawable(context, R.drawable.rainn);

            case "11d":
                return ContextCompat.getDrawable(context, R.drawable.thunderd);

            case "11n":
                return ContextCompat.getDrawable(context, R.drawable.thunderd);

            case "12d":
                return ContextCompat.getDrawable(context, R.drawable.snowd);

            case "12n":
                return ContextCompat.getDrawable(context, R.drawable.snowd);

            case "13d":
                return ContextCompat.getDrawable(context, R.drawable.mistd);

            case "13n":
                return ContextCompat.getDrawable(context, R.drawable.mistd);

            default:
                return ContextCompat.getDrawable(context, R.drawable.cloudyn);
        }
    }
}
