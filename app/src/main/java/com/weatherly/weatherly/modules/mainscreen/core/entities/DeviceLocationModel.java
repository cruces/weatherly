package com.weatherly.weatherly.modules.mainscreen.core.entities;

public class DeviceLocationModel {
    private String latitude;
    private String longitude;

    public DeviceLocationModel(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
