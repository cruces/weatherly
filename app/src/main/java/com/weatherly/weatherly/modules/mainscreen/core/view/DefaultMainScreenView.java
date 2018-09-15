package com.weatherly.weatherly.modules.mainscreen.core.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.common.openweathermap.IconUtils;
import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;

public class DefaultMainScreenView extends FrameLayout implements MainScreenView {
    private MainScreenViewOutput callbacks;
    private ImageView weatherIcon;
    private TextView weatherDescription;
    private TextView pressure;
    private TextView humidity;
    private TextView wind;
    private TextView cloudiness;
    private RelativeLayout container;
    private Context context;
    private Toolbar toolbar;
    private TextView description;
    private TextView buttonForecast;

    public DefaultMainScreenView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_main, this);
        this.context = context;

        weatherIcon = findViewById(R.id.weather_icon);
        weatherDescription = findViewById(R.id.weather_temp);
        container = findViewById(R.id.main_container);
        toolbar = findViewById(R.id.toolbar);
        pressure = findViewById(R.id.pressure);
        humidity = findViewById(R.id.humidity);
        wind = findViewById(R.id.wind);
        description = findViewById(R.id.weather_description);
        cloudiness = findViewById(R.id.cloudiness);
        buttonForecast = findViewById(R.id.button_forecast);

        buttonForecast.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                callbacks.onButtonClicked();
            }
        });
    }

    @Override
    public void setUpWeather(WeatherDataModel weather) {
        Drawable icon = IconUtils.getWeatherIcon(context, weather.getIconUrl());
        String desc = weather.getDescription();
        String upperDescription = desc.substring(0, 1).toUpperCase() + desc.substring(1);
        description.setText(upperDescription);
        weatherDescription.setText(weather.getWeatherTemp());
        toolbar.setTitle(weather.getCityName() + ", " + weather.getCountry());
        pressure.setText(weather.getPressure());
        humidity.setText(weather.getHumidity());
        wind.setText(weather.getWind());
        cloudiness.setText(weather.getClouds());
        Glide.with(this).load(icon).into(weatherIcon);
    }

    @Override
    public void setCallbacks(MainScreenViewOutput callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.color_transparent_gray));
    }
}
