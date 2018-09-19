package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

public interface ForecastInteractorOutput {
    void onGetForecastListSuccess(ForecastDataModel forecast);

    void onGetForecastListError(String e);
}
