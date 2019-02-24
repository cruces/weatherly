package com.weatherly.weatherly.modules.common.openweathermap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String convertDateShort(String dateString) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String pattern2 = "EE, dd MMM";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);

        Date date;
        String dateOutput = "";
        try {
            date = simpleDateFormat.parse(dateString);
            dateOutput = simpleDateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    public static String convertDateShortHour(String dateString) {
        String pattern = "yyyy-MM-dd H:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String pattern2 = "h:mm a";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);

        Date date = null;
        String dateOutput = "";
        try {
            date = simpleDateFormat.parse(dateString);
            dateOutput = simpleDateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOutput;
    }

    public static String getDateDay(String dateString) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String pattern2 = "dd";
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);

        Date date = null;
        String dateOutput = "";
        try {
            date = simpleDateFormat.parse(dateString);
            dateOutput = simpleDateFormat2.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateOutput;
    }
}
