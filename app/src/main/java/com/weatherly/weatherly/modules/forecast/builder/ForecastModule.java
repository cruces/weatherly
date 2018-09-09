package com.weatherly.weatherly.modules.forecast.builder;

import com.weatherly.weatherly.modules.forecast.ForecastActivity;
import com.weatherly.weatherly.modules.forecast.core.interactor.DefaultForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.presenter.ForecastPresenter;
import com.weatherly.weatherly.modules.forecast.core.view.DefaultForecastView;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ForecastModule {
    private ForecastActivity activity;

    public ForecastModule(ForecastActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ForecastScope
    public ForecastView provideForecastView() {
        return new DefaultForecastView(activity);
    }

    @Provides
    @ForecastScope
    public ForecastInteractor provideForecastInteractor(Retrofit retrofit) {
        return new DefaultForecastInteractor(retrofit);
    }

    @Provides
    @ForecastScope
    public ForecastPresenter provideForecastPresenter(ForecastInteractor interactor, ForecastView view) {
        return new ForecastPresenter(interactor, view);
    }
}
