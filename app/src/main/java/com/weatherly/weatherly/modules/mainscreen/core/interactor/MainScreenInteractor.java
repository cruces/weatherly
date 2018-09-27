package com.weatherly.weatherly.modules.mainscreen.core.interactor;

import com.weatherly.weatherly.modules.mainscreen.core.entities.DeviceLocationModel;

public interface MainScreenInteractor {
    void getWeatherByCityName();

    void getWeatherByCoordinates(String lat, String lon);

    void setCallbacks(MainScreenInteractorOutput callbacks);

    void checkPermission(int requestCode, int[] grantResults);

    void getDeviceLocation();

    DeviceLocationModel getLocation();
}
