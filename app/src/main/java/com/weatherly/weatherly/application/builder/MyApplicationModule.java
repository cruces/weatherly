package com.weatherly.weatherly.application.builder;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MyApplicationModule {
    public static final String URL = "https://api.openweathermap.org/data/2.5/";
    public static final String USER_KEY = "e76aff39e03c917e833f9120538a956c";
    public static final String UNITS = "metric";

    public MyApplicationModule() {

    }

    @Provides
    @MyApplicationScope
    public Retrofit provideRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
