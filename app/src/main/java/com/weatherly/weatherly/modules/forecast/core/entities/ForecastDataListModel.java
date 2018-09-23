package com.weatherly.weatherly.modules.forecast.core.entities;

import java.io.Serializable;

public class ForecastDataListModel implements Serializable {
    private String temp;
    private String date;
    private String icon;
    private String hour;

    public ForecastDataListModel(String temp, String date, String icon, String hour) {
        this.temp = temp;
        this.date = date;
        this.icon = icon;
        this.hour = hour;
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

    public String getHour() {
        return hour;
    }
}
