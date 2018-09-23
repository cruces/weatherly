package com.weatherly.weatherly.modules.forecast.core.entities;

import java.io.Serializable;

public class ForecastDataListModel implements Serializable {
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
