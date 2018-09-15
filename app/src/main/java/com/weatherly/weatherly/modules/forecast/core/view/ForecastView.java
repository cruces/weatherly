package com.weatherly.weatherly.modules.forecast.core.view;

import android.view.View;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

public interface ForecastView {
    View getView();

    void setUpForecastList(ArrayList<ForecastDataListModel> list);

    void setCallbacks(ForecastViewOutput callback);
}
