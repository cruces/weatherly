package com.weatherly.weatherly.modules.forecast.core.interactor;

public interface ForecastInteractor {
    void getForecastList(String lat, String lon);

    void setCallbacks(ForecastInteractorOutput callback);
}
