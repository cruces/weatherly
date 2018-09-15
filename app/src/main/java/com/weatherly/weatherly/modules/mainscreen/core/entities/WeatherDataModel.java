package com.weatherly.weatherly.modules.mainscreen.core.entities;

public class WeatherDataModel {
    private String cityName;
    private String weatherTemp;
    private String iconUrl;
    private String pressure;
    private String humidity;
    private String wind;
    private String description;
    private String country;
    private String clouds;

    public WeatherDataModel(String cityName, String weatherTemp,
                            String iconUrl, String pressure,
                            String humidity, String wind, String description,
                            String country, String clouds) {
        this.cityName = cityName;
        this.weatherTemp = weatherTemp;
        this.iconUrl = iconUrl;
        this.pressure = pressure;
        this.humidity = humidity;
        this.wind = wind;
        this.description = description;
        this.country = country;
        this.clouds = clouds;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherTemp() {
        return weatherTemp;
    }

    public String getIconUrl() {
        return iconUrl;
    }
    
    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getWind() {
        return wind;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getClouds() {
        return clouds;
    }
}
