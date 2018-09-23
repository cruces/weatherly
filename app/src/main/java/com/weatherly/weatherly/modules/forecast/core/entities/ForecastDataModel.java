package com.weatherly.weatherly.modules.forecast.core.entities;

public class ForecastDataModel {
    private String city;
    private String country;

    public ForecastDataModel(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
}
