package com.weatherly.weatherly.modules.forecast.core.presenter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.forecast.core.interactor.ForecastInteractor;
import com.weatherly.weatherly.modules.forecast.core.view.ForecastView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class DefaultForecastPresenterTest {
    private ForecastPresenter presenter;
    private ForecastInteractor interactor;
    private ForecastView view;
    private AppCompatActivity activity;
    private String latitude;
    private String longitude;
    private Intent intent;

    @Before
    public void setUp() {
        interactor = Mockito.mock(ForecastInteractor.class);
        view = Mockito.mock(ForecastView.class);
        presenter = new ForecastPresenter(interactor, view);
        activity = Mockito.mock(AppCompatActivity.class);
        intent = Mockito.mock(Intent.class);
        Mockito.when(activity.getIntent()).thenReturn(intent);

        Mockito.when(activity.getIntent().getStringExtra("lat")).thenReturn("12");
        Mockito.when(activity.getIntent().getStringExtra("lon")).thenReturn("14");
        latitude = activity.getIntent().getStringExtra("lat");
        longitude = activity.getIntent().getStringExtra("lon");
    }

    @Test
    public void checkOnCreateCalls() {
        presenter.onCreate(activity);
        Mockito.verify(interactor).getForecastList(latitude, longitude);
        Mockito.verify(view).setUpTabs(activity);
    }

    @Test
    public void checkOnGetForecastListSuccess() {
        ForecastDataModel forecast = Mockito.mock(ForecastDataModel.class);
        ArrayList<ArrayList<ForecastDataListModel>> lists = Mockito.mock(ArrayList.class);

        presenter.onGetForecastListSuccess(forecast, lists);
        Mockito.verify(view).setUpToolbar(forecast);
        Mockito.verify(view).setProgressBar(false);
        Mockito.verify(view).updateViewPager(lists);
    }

    @Test
    public void checkOnGetForecastListError() {
        String message = "Sorry, an error occurred";
        presenter.onGetForecastListError(message);
        Mockito.verify(view).getToast(message);
    }

    @Test
    public void checkOnSwipeRefresh() {
        presenter.onCreate(activity);
        presenter.onSwipeRefresh();
        Mockito.verify(interactor, Mockito.times(2)).getForecastList(latitude, longitude);
    }

}
