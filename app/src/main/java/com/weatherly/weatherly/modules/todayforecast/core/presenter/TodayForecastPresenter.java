package com.weatherly.weatherly.modules.todayforecast.core.presenter;

import android.util.Log;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;
import com.weatherly.weatherly.modules.todayforecast.core.interactor.TodayForecastInteractor;
import com.weatherly.weatherly.modules.todayforecast.core.view.TodayForecastView;

import java.util.ArrayList;

public class TodayForecastPresenter {
    private TodayForecastInteractor interactor;
    private TodayForecastView view;

    public TodayForecastPresenter(TodayForecastInteractor interactor, TodayForecastView view) {
        this.interactor = interactor;
        this.view = view;
    }

    public void onCreate(ArrayList<ForecastDataListModel> forecast) {
        view.getView();
        Log.d("dume", "la list en el fragment oncreate" + forecast.size());
        view.setUpForecastList(forecast);
    }

}
