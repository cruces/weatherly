package com.weatherly.weatherly.modules.forecast.core.entities;

import java.util.List;

public class ForecastDataModel {
    private String city;
    private String country;
    private List<ForecastDataListModel> list;

    public ForecastDataModel(String city, String country, List<ForecastDataListModel> list) {
        this.city = city;
        this.country = country;
        this.list = list;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public List<ForecastDataListModel> getList() {
        return list;
    }
}
