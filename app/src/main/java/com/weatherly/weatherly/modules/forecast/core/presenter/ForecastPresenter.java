package com.weatherly.weatherly.modules.forecast.core.presenter;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractorOutput;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;

import java.util.ArrayList;

public class ForecastPresenter implements ForecastInteractorOutput {
    private ForecastInteractor interactor;
    private ForecastView view;

    public ForecastPresenter(ForecastInteractor interactor, ForecastView view) {
        this.interactor = interactor;
        this.view = view;
        interactor.setCallbacks(this);
    }

    public void onCreate() {
        interactor.getForecastList();
    }

    @Override
    public void onGetForecastListSuccess(ForecastDataModel forecast) {
        view.setUpForecastList((ArrayList<ForecastDataListModel>) forecast.getList());
    }

    @Override
    public void onGetForecastListError(String e) {

    }
}
