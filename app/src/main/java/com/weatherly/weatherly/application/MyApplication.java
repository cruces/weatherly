package com.weatherly.weatherly.application;

import android.app.Application;

public class MyApplication extends Application {
    private static AppInjector injector;

    public static AppInjector getInjector() {
        return injector;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        injector = new AppInjector();
    }
}
