package com.weatherly.weatherly.modules.forecast.core.presenter;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractorOutput;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastViewOutput;

import java.util.ArrayList;

public class ForecastPresenter implements ForecastInteractorOutput, ForecastViewOutput {
    private ForecastInteractor interactor;
    private ForecastView view;
    private AppCompatActivity activity;

    public ForecastPresenter(ForecastInteractor interactor, ForecastView view) {
        this.interactor = interactor;
        this.view = view;
        interactor.setCallbacks(this);
        view.setCallbacks(this);
    }

    public void onCreate(AppCompatActivity appCompatActivity) {
        activity = appCompatActivity;
        interactor.getForecastList();
        view.setUpTabs(activity);
    }

    @Override
    public void onGetForecastListSuccess(ForecastDataModel forecast,
                                         ArrayList<ArrayList<ForecastDataListModel>> lists) {
        view.setUpToolbar(forecast);
        view.setProgressBar(false);
        view.updateViewPager(lists);
    }

    @Override
    public void onGetForecastListError(String e) {
        view.getToast("Sorry, an error occurred");
    }

    @Override
    public void onSwipeRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                interactor.getForecastList();
            }
        }, 1000);
    }
}
