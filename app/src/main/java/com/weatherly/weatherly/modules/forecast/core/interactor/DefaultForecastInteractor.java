package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.weatherly.weatherly.modules.common.WeatherRequest;

import retrofit2.Retrofit;

public class DefaultForecastInteractor implements ForecastInteractor {
    private Retrofit retrofit;
    private WeatherRequest request;

    public DefaultForecastInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
        request = retrofit.create(WeatherRequest.class);
    }
}
