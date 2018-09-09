package com.weatherly.weatherly.modules.common.entities;

import java.util.List;

public class WeatherGeneralModel {
    private CoordinatesModel coord;
    private List<WeatherModel> weather;
    private MainModel main;
    private String name;
    private WindModel wind;
    private SysModel sys;
    private CloudsModel clouds;

    public CoordinatesModel getCoord() {
        return coord;
    }

    public List<WeatherModel> getWeather() {
        return weather;
    }

    public MainModel getMain() {
        return main;
    }

    public String getName() {
        return name;
    }

    public WindModel getWind() {
        return wind;
    }

    public SysModel getSys() {
        return sys;
    }

    public CloudsModel getClouds() {
        return clouds;
    }
}
