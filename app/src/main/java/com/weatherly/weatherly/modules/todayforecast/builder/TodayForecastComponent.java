package com.weatherly.weatherly.modules.todayforecast.builder;

import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;

import dagger.Component;

@TodayForecastScope
@Component(modules = {TodayForecastModule.class}, dependencies = {MyApplicationComponent.class})
public interface TodayForecastComponent {
    void inject(TodayForecastFragment fragment);
}
