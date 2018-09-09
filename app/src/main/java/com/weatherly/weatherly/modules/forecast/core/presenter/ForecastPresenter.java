package com.weatherly.weatherly.modules.forecast.core.presenter;

import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;

public class ForecastPresenter {
    private ForecastInteractor interactor;
    private ForecastView view;

    public ForecastPresenter(ForecastInteractor interactor, ForecastView view) {
        this.interactor = interactor;
        this.view = view;
    }

    public void onCreate() {

    }
}
