package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import java.util.ArrayList;

public interface ForecastInteractorOutput {
    void onGetForecastListSuccess(ForecastDataModel forecast,
                                  ArrayList<ArrayList<ForecastDataListModel>> lists);

    void onGetForecastListError(String e);
}
