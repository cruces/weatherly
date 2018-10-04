package com.weatherly.weatherly.modules.about.builder;

import com.weatherly.weatherly.application.builder.MyApplicationComponent;
import com.weatherly.weatherly.modules.about.AboutActivity;

import dagger.Component;

@AboutScope
@Component(modules = {AboutModule.class}, dependencies = {MyApplicationComponent.class})
public interface AboutComponent {
    void inject(AboutActivity activity);
}
