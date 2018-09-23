package com.weatherly.weatherly.application;

import com.weatherly.weatherly.application.builder.DaggerMyApplicationComponent;
import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.application.builder.MyApplicationModule;
import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;
import com.weatherly.weatherly.modules.extendedforecast.builder.DaggerExtendedForecastComponent;
import com.weatherly.weatherly.modules.extendedforecast.builder.ExtendedForecastModule;
import com.weatherly.weatherly.modules.forecast.ForecastActivity;
import com.weatherly.weatherly.modules.forecast.builder.DaggerForecastComponent;
import com.weatherly.weatherly.modules.forecast.builder.ForecastModule;
import com.weatherly.weatherly.modules.mainscreen.MainScreenActivity;
import com.weatherly.weatherly.modules.mainscreen.builder.DaggerMainScreenComponent;
import com.weatherly.weatherly.modules.mainscreen.builder.MainScreenModule;

public class AppInjector {
    private MyApplicationComponent component;

    public AppInjector() {
        this.component = DaggerMyApplicationComponent
                .builder()
                .myApplicationModule(new MyApplicationModule())
                .build();
    }

    public void inject(MainScreenActivity activity) {
        DaggerMainScreenComponent.builder()
                .mainScreenModule(new MainScreenModule(activity))
                .myApplicationComponent(component)
                .build()
                .inject(activity);
    }

    public void injectForecast(ForecastActivity activity) {
        DaggerForecastComponent.builder()
                .forecastModule(new ForecastModule(activity))
                .myApplicationComponent(component)
                .build()
                .inject(activity);
    }

    public void injectTodayForecast(ExtendedForecastFragment fragment) {
        DaggerExtendedForecastComponent.builder()
                .extendedForecastModule(new ExtendedForecastModule(fragment))
                .myApplicationComponent(component)
                .build()
                .inject(fragment);
    }
}
