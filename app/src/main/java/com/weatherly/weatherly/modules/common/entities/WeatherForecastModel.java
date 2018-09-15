package com.weatherly.weatherly.modules.common.entities;

import java.util.List;

public class WeatherForecastModel {
    private int dt;
    private List<WeatherModel> weather;
    private MainModel main;
    private WindModel wind;
    private String dt_txt;

    public int getDt() {
        return dt;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public MainModel getMain() {
        return main;
    }

    public WindModel getWind() {
        return wind;
    }

    public String getDt_txt() {
        return dt_txt;
    }
}
