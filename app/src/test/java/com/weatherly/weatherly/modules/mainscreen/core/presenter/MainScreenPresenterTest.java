package com.weatherly.weatherly.modules.mainscreen.core.presenter;

import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;
import com.weatherly.weatherly.modules.mainscreen.core.interactor.MainScreenInteractor;
import com.weatherly.weatherly.modules.mainscreen.core.view.MainScreenView;
import com.weatherly.weatherly.modules.mainscreen.wireframe.MainScreenWireframe;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class MainScreenPresenterTest {
    private MainScreenPresenter presenter;
    private MainScreenInteractor interactor;
    private MainScreenView view;
    private MainScreenWireframe wireframe;

    @Before
    public void setUp() {
        interactor = Mockito.mock(MainScreenInteractor.class);
        view = Mockito.mock(MainScreenView.class);
        wireframe = Mockito.mock(MainScreenWireframe.class);
        presenter = new MainScreenPresenter(interactor, view, wireframe);
    }

    @Test
    public void checkOnCreateCalls() {
        presenter.onCreate();

        Mockito.verify(interactor).getDeviceLocation();
        Mockito.verify(view).getToolbar();
    }

    @Test
    public void checkOnCheckPermissions() {
        // Check from interactor
        int requestCode = 1;
        int[] grantResults = {1, 2};
        presenter.onCheckPermissions(requestCode, grantResults);

        Mockito.verify(interactor).checkPermission(requestCode, grantResults);
    }

    @Test
    public void checkOnGetWeatherByCityNameSuccess() {
        WeatherDataModel weatherDataModelMock = Mockito.mock(WeatherDataModel.class);
        presenter.onGetWeatherByCityNameSuccess(weatherDataModelMock);

        Mockito.verify(view).setUpWeather(weatherDataModelMock);
        Mockito.verify(view).setProgressBar(Mockito.anyBoolean());
    }

    @Test
    public void checkOnGetWeatherByCityNameError() {
        String message = "Sorry, an error occurred";
        presenter.onGetWeatherByCityNameError(message);
        Mockito.verify(view).getToast(message);
    }
}
