package com.weatherly.weatherly.modules.common.entities;

import java.util.List;

public class ForecastGeneralModel {
    private String cod;
    private int cnt;
    private List<WeatherForecastModel> list;

    public String getCod() {
        return cod;
    }

    public int getCnt() {
        return cnt;
    }

    public List<WeatherForecastModel> getList() {
        return list;
    }
}
