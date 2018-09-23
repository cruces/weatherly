package com.weatherly.weatherly.modules.todayforecast.core.view;

import android.view.View;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;

import java.util.ArrayList;

public interface TodayForecastView {
    View getView();
    void setUpForecastList(ArrayList<ForecastDataListModel> list);

    void setOnSwipeToRefresh(TodayForecastFragment.OnSwipeToRefresh swipeToRefresh);
}
