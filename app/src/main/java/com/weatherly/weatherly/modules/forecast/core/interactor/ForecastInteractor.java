package com.weatherly.weatherly.modules.forecast.core.interactor;

public interface ForecastInteractor {
    void getForecastList();

    void setCallbacks(ForecastInteractorOutput callback);
}
