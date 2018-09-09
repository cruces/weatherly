package com.weatherly.weatherly.application.builder;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {MyApplicationModule.class})
@MyApplicationScope
public interface MyApplicationComponent {
    Retrofit getRetrofit();
}
