package com.weatherly.weatherly.modules.common.openweathermap;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.weatherly.weatherly.R;

public class IconUtils {
    public static Drawable getWeatherIcon(Context context, String code) {
        switch (code) {
            case "04d":
                return ContextCompat.getDrawable(context, R.mipmap.cloudyn);

            case "04n":
                return ContextCompat.getDrawable(context, R.mipmap.cloudyn);

            case "01d":
                return ContextCompat.getDrawable(context, R.mipmap.cleard);

            case "01n":
                return ContextCompat.getDrawable(context, R.mipmap.clearn);

            case "02d":
                return ContextCompat.getDrawable(context, R.mipmap.fewcloudd);

            case "02n":
                return ContextCompat.getDrawable(context, R.mipmap.fewcloudn);

            case "03d":
                return ContextCompat.getDrawable(context, R.mipmap.scatteredd);

            case "03n":
                return ContextCompat.getDrawable(context, R.mipmap.scatteredd);

            case "09d":
                return ContextCompat.getDrawable(context, R.mipmap.showerd);

            case "09n":
                return ContextCompat.getDrawable(context, R.mipmap.showerd);

            case "10d":
                return ContextCompat.getDrawable(context, R.mipmap.raind);

            case "10n":
                return ContextCompat.getDrawable(context, R.mipmap.rainn);

            case "11d":
                return ContextCompat.getDrawable(context, R.mipmap.thunderd);

            case "11n":
                return ContextCompat.getDrawable(context, R.mipmap.thunderd);

            case "12d":
                return ContextCompat.getDrawable(context, R.mipmap.snowd);

            case "12n":
                return ContextCompat.getDrawable(context, R.mipmap.snowd);

            case "13d":
                return ContextCompat.getDrawable(context, R.mipmap.mistd);

            case "13n":
                return ContextCompat.getDrawable(context, R.mipmap.mistd);

            default:
                return ContextCompat.getDrawable(context, R.mipmap.cloudyn);
        }
    }
}
