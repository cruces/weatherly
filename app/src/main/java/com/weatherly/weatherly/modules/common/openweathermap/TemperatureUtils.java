package com.weatherly.weatherly.modules.common.openweathermap;

public class TemperatureUtils {
    public static String parseTemperature(String temp) {
        double doubleTemp = Double.parseDouble(temp);
        return String.valueOf((int) doubleTemp);
    }
}
