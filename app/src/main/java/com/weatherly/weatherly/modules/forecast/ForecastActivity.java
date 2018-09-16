package com.weatherly.weatherly.modules.forecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.common.openweathermap.ThemeUtils;
import com.weatherly.weatherly.modules.forecast.core.presenter.ForecastPresenter;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;

import javax.inject.Inject;

public class ForecastActivity extends AppCompatActivity {

    @Inject
    public ForecastPresenter presenter;

    @Inject
    public ForecastView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getInjector().inject(this);
        ThemeUtils.setAppTheme(this);

        setContentView(view.getView());
        presenter.onCreate();

        setSupportActionBar(view.getToolbar());
        view.setStatusBarColor(this);
    }
}
