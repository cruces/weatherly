package com.weatherly.weatherly.modules.extendedforecast.builder;

import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;
import com.weatherly.weatherly.modules.extendedforecast.core.interactor.DefaultExtendedForecastInteractor;
import com.weatherly.weatherly.modules.extendedforecast.core.interactor.ExtendedForecastInteractor;
import com.weatherly.weatherly.modules.extendedforecast.core.presenter.ExtendedForecastPresenter;
import com.weatherly.weatherly.modules.extendedforecast.core.view.DefaultExtendedForecastView;
import com.weatherly.weatherly.modules.extendedforecast.core.view.ExtendedForecastView;

import dagger.Module;
import dagger.Provides;

@Module
public class ExtendedForecastModule {
    private ExtendedForecastFragment fragment;

    public ExtendedForecastModule(ExtendedForecastFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @ExtendedForecastScope
    public ExtendedForecastView provideTodayForecastView() {
        return new DefaultExtendedForecastView(fragment.getContext());
    }

    @Provides
    @ExtendedForecastScope
    public ExtendedForecastPresenter provideTodayForecastPresenter(ExtendedForecastInteractor interactor,
                                                                   ExtendedForecastView view) {
        return new ExtendedForecastPresenter(interactor, view);
    }

    @Provides
    @ExtendedForecastScope
    public ExtendedForecastInteractor provideTodayForecastInteractor() {
        return new DefaultExtendedForecastInteractor();
    }

}
