package com.weatherly.weatherly.modules.extendedforecast.core.view;

import android.view.View;

import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

public interface ExtendedForecastView {
    View getView();
    void setUpForecastList(ArrayList<ForecastDataListModel> list);

    void setOnSwipeToRefresh(ExtendedForecastFragment.OnSwipeToRefresh swipeToRefresh);
}
