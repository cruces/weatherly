package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.google.common.truth.Truth;
import com.weatherly.weatherly.modules.common.WeatherRequest;
import com.weatherly.weatherly.modules.common.entities.CityModel;
import com.weatherly.weatherly.modules.common.entities.ForecastGeneralModel;
import com.weatherly.weatherly.modules.common.entities.MainModel;
import com.weatherly.weatherly.modules.common.entities.WeatherForecastModel;
import com.weatherly.weatherly.modules.common.entities.WeatherModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DefaultForecastInteractorTest {
    private DefaultForecastInteractor interactor;
    private WeatherRequest requestMock;
    private Call<ForecastGeneralModel> callGeneralModelMock;
    private ForecastInteractorOutput forecastCallbackMock;
    private Response<ForecastGeneralModel> responseGeneralMock;
    private ForecastGeneralModel forecastGeneralModelMock;

    @Captor
    private ArgumentCaptor<ArrayList<ArrayList<ForecastDataListModel>>> forecastListCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        Retrofit retrofitMock = Mockito.mock(Retrofit.class);
        interactor = Mockito.mock(DefaultForecastInteractor.class);
        forecastCallbackMock = Mockito.mock(ForecastInteractorOutput.class);

        callGeneralModelMock = Mockito.mock(Call.class);
        requestMock = Mockito.mock(WeatherRequest.class);
        responseGeneralMock = Mockito.mock(Response.class);
        forecastGeneralModelMock = Mockito.mock(ForecastGeneralModel.class);

        Mockito.when(requestMock.getForecastNextDaysByCoordinates(Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(callGeneralModelMock);
        Mockito.when(retrofitMock.create(WeatherRequest.class)).thenReturn(requestMock);

        interactor = new DefaultForecastInteractor(retrofitMock);
        interactor.setCallbacks(forecastCallbackMock);
    }

    @Test
    public void checkOnGetForecastList_callGetForecastNextDaysByCoordinates() {
        interactor.getForecastList("12", "16.2");

        Mockito.verify(requestMock).getForecastNextDaysByCoordinates("12", "16.2", "metric",
                "e76aff39e03c917e833f9120538a956c");
    }

    @Test
    public void checkOnGetForecastList_onFailure() {
        Callback<ForecastGeneralModel> retrofitCallback = getRetrofitCallback();
        retrofitCallback.onFailure(callGeneralModelMock, Mockito.mock(Throwable.class));
        Mockito.verify(forecastCallbackMock).onGetForecastListError(Mockito.anyString());
    }

    @Test
    public void checkOnGetForecastList_onSuccessCallOnGetForecastListError() {
        Callback<ForecastGeneralModel> retrofitCallback = getRetrofitCallback();
        retrofitCallback.onResponse(callGeneralModelMock, responseGeneralMock);
        Mockito.verify(forecastCallbackMock).onGetForecastListError(Mockito.anyString());
    }

    @Test
    public void checkOnGetForecastList_onSuccessCallOnGetForecastListSuccess() {
        Callback<ForecastGeneralModel> retrofitCallback = getRetrofitCallback();

        ArgumentCaptor forecastCallbackCaptor = ArgumentCaptor.forClass(ForecastDataModel.class);

        Mockito.when(responseGeneralMock.body()).thenReturn(forecastGeneralModelMock);
        ForecastDataListModel forecastDataModelMock = Mockito.mock(ForecastDataListModel.class);

        List<WeatherForecastModel> modelListMock = new ArrayList<>();
        Mockito.when(forecastGeneralModelMock.getList()).thenReturn(modelListMock);

        WeatherForecastModel model = Mockito.mock(WeatherForecastModel.class);

        Mockito.when(model.getDt_txt()).thenReturn("2017-01-30 18:00:00");

        MainModel mainModelMock = Mockito.mock(MainModel.class);

        Mockito.when(mainModelMock.getTemp()).thenReturn("12");
        Mockito.when(forecastDataModelMock.getTemp()).thenReturn("12");

        Mockito.when(model.getMain()).thenReturn(mainModelMock);
        Mockito.when(forecastDataModelMock.getDate()).thenReturn("2017-01-30 18:00:00");

        List<WeatherModel> weatherModelMock = Mockito.mock(List.class);

        Mockito.when(forecastDataModelMock.getIcon()).thenReturn("cloud.png");

        Mockito.when(forecastDataModelMock.getHour()).thenReturn("18:00:00");

        Mockito.when(model.getWeather()).thenReturn(weatherModelMock);

        CityModel cityModelMock = Mockito.mock(CityModel.class);
        Mockito.when(cityModelMock.getName()).thenReturn("Dublin");
        Mockito.when(cityModelMock.getCountry()).thenReturn("Ireland");
        Mockito.when(forecastGeneralModelMock.getCity()).thenReturn(cityModelMock);

        retrofitCallback.onResponse(callGeneralModelMock, responseGeneralMock);

        Mockito.verify(forecastCallbackMock)
                .onGetForecastListSuccess((ForecastDataModel) forecastCallbackCaptor.capture(), forecastListCaptor.capture());

        ForecastDataModel dataModel = (ForecastDataModel) forecastCallbackCaptor.getValue();

        Truth.assertThat(dataModel.getCity()).isEqualTo("Dublin");
        Truth.assertThat(dataModel.getCountry()).isEqualTo("Ireland");

    }

    private Callback<ForecastGeneralModel> getRetrofitCallback() {
        interactor.getForecastList("12", "16.2");

        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<ForecastGeneralModel>) retrofitCallbackCaptor.capture());

        return (Callback<ForecastGeneralModel>) retrofitCallbackCaptor.getValue();
    }
}
