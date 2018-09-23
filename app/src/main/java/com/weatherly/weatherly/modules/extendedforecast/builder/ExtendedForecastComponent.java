package com.weatherly.weatherly.modules.extendedforecast.builder;

import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;

import dagger.Component;

@ExtendedForecastScope
@Component(modules = {ExtendedForecastModule.class}, dependencies = {MyApplicationComponent.class})
public interface ExtendedForecastComponent {
    void inject(ExtendedForecastFragment fragment);
}
