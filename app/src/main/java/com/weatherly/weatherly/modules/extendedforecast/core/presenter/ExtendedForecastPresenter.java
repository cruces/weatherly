package com.weatherly.weatherly.modules.extendedforecast.core.presenter;

import com.weatherly.weatherly.modules.extendedforecast.core.view.ExtendedForecastView;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.extendedforecast.core.interactor.ExtendedForecastInteractor;

import java.util.ArrayList;

public class ExtendedForecastPresenter {
    private ExtendedForecastInteractor interactor;
    private ExtendedForecastView view;

    public ExtendedForecastPresenter(ExtendedForecastInteractor interactor, ExtendedForecastView view) {
        this.interactor = interactor;
        this.view = view;
    }

    public void onCreate(ArrayList<ForecastDataListModel> forecast) {
        view.getView();
        view.setUpForecastList(forecast);
    }

}
