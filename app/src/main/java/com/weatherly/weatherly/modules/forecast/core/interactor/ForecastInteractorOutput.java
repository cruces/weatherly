package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.weatherly.weatherly.modules.common.entities.ForecastGeneralModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import java.text.ParseException;

public interface ForecastInteractorOutput {
    void onGetForecastListSuccess(ForecastDataModel forecast);

    void onGetForecastListError(String e);
}
