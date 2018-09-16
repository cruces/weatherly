package com.weatherly.weatherly.modules.forecast.core.view;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import java.util.ArrayList;

public interface ForecastView {
    View getView();

    void setUpForecastList(ArrayList<ForecastDataListModel> list);

    void setCallbacks(ForecastViewOutput callback);

    Toolbar getToolbar();

    void setStatusBarColor(Activity activity);

    void setUpToolbar(ForecastDataModel forecast);
}
