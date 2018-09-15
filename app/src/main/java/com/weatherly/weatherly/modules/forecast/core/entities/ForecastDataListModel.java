package com.weatherly.weatherly.modules.forecast.core.entities;

public class ForecastDataListModel {
    private String temp;
    private String date;
    private String icon;

    public ForecastDataListModel(String temp, String date, String icon) {
        this.temp = temp;
        this.date = date;
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public String getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }
}
