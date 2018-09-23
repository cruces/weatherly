package com.weatherly.weatherly.modules.forecast.core.view;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import java.util.ArrayList;

public interface ForecastView {
    View getView();

    void setCallbacks(ForecastViewOutput callback);

    Toolbar getToolbar();

    void setStatusBarColor(Activity activity);

    void setUpToolbar(ForecastDataModel forecast);

    void setProgressBar(boolean status);

    void getToast(String message);

    void setUpTabs(AppCompatActivity activity);

    void updateViewPager(ArrayList<ArrayList<ForecastDataListModel>> list);
}
