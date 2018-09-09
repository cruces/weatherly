package com.weatherly.weatherly.modules.mainscreen.core.interactor;

import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;

public interface MainScreenInteractorOutput {
    void onGetWeatherByCityNameSuccess(WeatherDataModel weather);

    void onGetWeatherByCityNameError(String e);
}
