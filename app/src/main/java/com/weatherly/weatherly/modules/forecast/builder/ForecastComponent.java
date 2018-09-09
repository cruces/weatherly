package com.weatherly.weatherly.modules.forecast.builder;

import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.modules.forecast.ForecastActivity;

import dagger.Component;

@ForecastScope
@Component(modules = {ForecastModule.class}, dependencies = {MyApplicationComponent.class})
public interface ForecastComponent {
    void inject(ForecastActivity activity);
}
