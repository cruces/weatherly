package com.weatherly.weatherly.modules.mainscreen.builder;

import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.modules.mainscreen.MainScreenActivity;

import dagger.Component;

@MainScreenScope
@Component(modules = {MainScreenModule.class}, dependencies = {MyApplicationComponent.class})
public interface MainScreenComponent {
    void inject(MainScreenActivity activity);
}
