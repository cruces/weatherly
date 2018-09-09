package com.weatherly.weatherly.modules.mainscreen.core.interactor;

public interface MainScreenInteractor {
    void getWeatherByCityName();

    void setCallbacks(MainScreenInteractorOutput callbacks);

    String parseTemperature(String temp);
}
