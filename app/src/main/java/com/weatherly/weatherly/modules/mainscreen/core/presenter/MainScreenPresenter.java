package com.weatherly.weatherly.modules.mainscreen.core.presenter;

import android.os.Handler;

import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;
import com.weatherly.weatherly.modules.mainscreen.core.interactor.MainScreenInteractor;
import com.weatherly.weatherly.modules.mainscreen.core.interactor.MainScreenInteractorOutput;
import com.weatherly.weatherly.modules.mainscreen.core.view.MainScreenView;
import com.weatherly.weatherly.modules.mainscreen.core.view.MainScreenViewOutput;
import com.weatherly.weatherly.modules.mainscreen.wireframe.MainScreenWireframe;

public class MainScreenPresenter implements MainScreenViewOutput, MainScreenInteractorOutput {
    private MainScreenInteractor interactor;
    private MainScreenView view;
    private MainScreenWireframe wireframe;

    public MainScreenPresenter(MainScreenInteractor interactor, MainScreenView view,
                               MainScreenWireframe wireframe) {
        this.interactor = interactor;
        this.view = view;
        this.wireframe = wireframe;
        view.setCallbacks(this);
        interactor.setCallbacks(this);
    }

    public void onCreate() {
        interactor.getDeviceLocation();
        view.getToolbar();
    }

    @Override
    public void onCheckPermissions(int requestCode, int[] grantResults) {
        interactor.checkPermission(requestCode, grantResults);
    }

    @Override
    public void onGetWeatherByCityNameSuccess(WeatherDataModel weather) {
        view.setUpWeather(weather);
        view.setProgressBar(false);
    }

    @Override
    public void onGetWeatherByCityNameError(String e) {
        view.getToast("Sorry, an error occurred");
    }

    @Override
    public void onButtonClicked() {
        wireframe.openForecastScreen(interactor.getLocation().getLatitude(),
                interactor.getLocation().getLongitude());
    }

    @Override
    public void onMenuItemClicked() {
        wireframe.openAboutScreen();
    }

    @Override
    public void onSwipe() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                interactor.getDeviceLocation();
            }
        }, 1000);
    }
}
