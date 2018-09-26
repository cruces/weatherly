package com.weatherly.weatherly.modules.mainscreen.core.interactor;

public interface MainScreenInteractor {
    void getWeatherByCityName();

    void getWeatherByCoordinates(String lat, String lon);

    void setCallbacks(MainScreenInteractorOutput callbacks);

    void checkPermission(int requestCode, int[] grantResults);

    void getDeviceLocation();
}
