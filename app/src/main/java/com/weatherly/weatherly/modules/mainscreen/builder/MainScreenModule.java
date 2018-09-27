package com.weatherly.weatherly.modules.mainscreen.builder;

import com.weatherly.weatherly.modules.mainscreen.MainScreenActivity;
import com.weatherly.weatherly.modules.mainscreen.core.interactor.DefaultMainScreenInteractor;
import com.weatherly.weatherly.modules.mainscreen.core.interactor.MainScreenInteractor;
import com.weatherly.weatherly.modules.mainscreen.core.presenter.MainScreenPresenter;
import com.weatherly.weatherly.modules.mainscreen.core.view.DefaultMainScreenView;
import com.weatherly.weatherly.modules.mainscreen.core.view.MainScreenView;
import com.weatherly.weatherly.modules.mainscreen.wireframe.DefaultMainScreenWireframe;
import com.weatherly.weatherly.modules.mainscreen.wireframe.MainScreenWireframe;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainScreenModule {
    private MainScreenActivity activity;

    public MainScreenModule(MainScreenActivity activity) {
        this.activity = activity;
    }

    @Provides
    @MainScreenScope
    public MainScreenView provideMainView() {
        return new DefaultMainScreenView(activity);
    }

    @Provides
    @MainScreenScope
    public MainScreenInteractor provideMainInteractor(Retrofit retrofit) {
        return new DefaultMainScreenInteractor(activity, retrofit);
    }

    @Provides
    @MainScreenScope
    public MainScreenPresenter provideMainPresenter(MainScreenInteractor interactor,
                                                    MainScreenView view,
                                                    MainScreenWireframe wireframe) {
        return new MainScreenPresenter(interactor, view, wireframe);
    }

    @Provides
    @MainScreenScope
    public MainScreenWireframe provideMainWireframe() {
        return new DefaultMainScreenWireframe(activity);
    }
}
