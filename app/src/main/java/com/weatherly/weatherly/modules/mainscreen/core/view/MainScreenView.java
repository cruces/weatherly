package com.weatherly.weatherly.modules.mainscreen.core.view;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;

public interface MainScreenView {
    void setCallbacks(MainScreenViewOutput callbacks);

    View getView();

    void setUpWeather(WeatherDataModel weather);

    Toolbar getToolbar();

    void setStatusBarColor(Activity activity);

    void setProgressBar(boolean status);

    void setMainMenu(Activity activity, Menu menu);

    void getToast(String message);
}
