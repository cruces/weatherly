package com.weatherly.weatherly.modules.todayforecast.builder;

import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;
import com.weatherly.weatherly.modules.todayforecast.core.interactor.DefaultTodayForecastInteractor;
import com.weatherly.weatherly.modules.todayforecast.core.interactor.TodayForecastInteractor;
import com.weatherly.weatherly.modules.todayforecast.core.presenter.TodayForecastPresenter;
import com.weatherly.weatherly.modules.todayforecast.core.view.DefaultTodayForecastView;
import com.weatherly.weatherly.modules.todayforecast.core.view.TodayForecastView;

import dagger.Module;
import dagger.Provides;

@Module
public class TodayForecastModule {
    private TodayForecastFragment fragment;

    public TodayForecastModule(TodayForecastFragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @TodayForecastScope
    public TodayForecastView provideTodayForecastView() {
        return new DefaultTodayForecastView(fragment.getContext());
    }

    @Provides
    @TodayForecastScope
    public TodayForecastPresenter provideTodayForecastPresenter(TodayForecastInteractor interactor,
                                                                TodayForecastView view) {
        return new TodayForecastPresenter(interactor, view);
    }

    @Provides
    @TodayForecastScope
    public TodayForecastInteractor provideTodayForecastInteractor() {
        return new DefaultTodayForecastInteractor();
    }

}
