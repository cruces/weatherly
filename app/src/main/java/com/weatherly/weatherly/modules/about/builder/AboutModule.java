package com.weatherly.weatherly.modules.about.builder;

import com.weatherly.weatherly.modules.about.AboutActivity;
import com.weatherly.weatherly.modules.about.core.interactor.AboutInteractor;
import com.weatherly.weatherly.modules.about.core.interactor.DefaultAboutInteractor;
import com.weatherly.weatherly.modules.about.core.presenter.AboutPresenter;
import com.weatherly.weatherly.modules.about.core.view.AboutView;
import com.weatherly.weatherly.modules.about.core.view.DefaultAboutView;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutModule {
    private AboutActivity activity;

    public AboutModule(AboutActivity activity) {
        this.activity = activity;
    }

    @Provides
    @AboutScope
    public AboutInteractor provideAboutInteractor() {
        return new DefaultAboutInteractor();
    }

    @Provides
    @AboutScope
    public AboutView provideAboutView() {
        return new DefaultAboutView(activity);
    }

    @Provides
    @AboutScope
    public AboutPresenter providePresenter() {
        return new AboutPresenter();
    }
}
