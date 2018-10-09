package com.weatherly.weatherly.modules.mainscreen.core.interactor;

import android.content.Context;

import com.google.common.truth.Truth;
import com.weatherly.weatherly.application.builder.MyApplicationModule;
import com.weatherly.weatherly.modules.common.WeatherRequest;
import com.weatherly.weatherly.modules.common.entities.CloudsModel;
import com.weatherly.weatherly.modules.common.entities.MainModel;
import com.weatherly.weatherly.modules.common.entities.SysModel;
import com.weatherly.weatherly.modules.common.entities.WeatherGeneralModel;
import com.weatherly.weatherly.modules.common.entities.WeatherModel;
import com.weatherly.weatherly.modules.common.entities.WindModel;
import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DefaultMainScreenInteractorTest {
    private DefaultMainScreenInteractor interactor;
    private WeatherRequest requestMock;
    private Call<WeatherGeneralModel> callGeneralModelMock;
    private MainScreenInteractorOutput myCallback;
    private Response<WeatherGeneralModel> responseWeatherGeneralModelMock;
    private WeatherGeneralModel weatherGeneralModelMock;

    @Before
    public void setUp() {
        Context context = Mockito.mock(Context.class);
        Retrofit retrofit = Mockito.mock(Retrofit.class);
        myCallback = Mockito.mock(MainScreenInteractorOutput.class);

        requestMock = Mockito.mock(WeatherRequest.class);
        callGeneralModelMock = Mockito.mock(Call.class);
        responseWeatherGeneralModelMock = Mockito.mock(Response.class);
        weatherGeneralModelMock = Mockito.mock(WeatherGeneralModel.class);

        Mockito.when(requestMock.getByCityName(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString())).thenReturn(callGeneralModelMock);
        Mockito.when(requestMock.getByCoordinates(Mockito.anyString(), Mockito.anyString(),
                Mockito.anyString(), Mockito.anyString())).thenReturn(callGeneralModelMock);
        Mockito.when(retrofit.create(WeatherRequest.class)).thenReturn(requestMock);

        interactor = new DefaultMainScreenInteractor(context, retrofit);
        interactor.setCallbacks(myCallback);
    }

    @Test
    public void checkOnGetWeatherByCityName_callGetByCityName() {
        interactor.getWeatherByCityName();

        Mockito.verify(requestMock).getByCityName("Dublin", MyApplicationModule.UNITS,
                MyApplicationModule.USER_KEY);
    }

    @Test
    public void checkOnGetWeatherByCityName_onFailure() {
        // Call callback's method

        interactor.getWeatherByCityName();

        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        retrofitCallback.onFailure(callGeneralModelMock, Mockito.mock(Throwable.class));

        Mockito.verify(myCallback).onGetWeatherByCityNameError(Mockito.anyString());
    }

    @Test
    public void checkOnGetWeatherByCityName_onSuccessCallOnGetWeatherByCityNameError() {
        // Call callback's method

        interactor.getWeatherByCityName();

        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        retrofitCallback.onResponse(callGeneralModelMock, responseWeatherGeneralModelMock);

        Mockito.verify(myCallback).onGetWeatherByCityNameError(Mockito.anyString());
    }

    @Test
    public void checkOnGetWeatherByCityName_onSuccessCallOnGetWeatherByCityNameSuccess() {
        // Call callback's method

        interactor.getWeatherByCityName();
        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);
        ArgumentCaptor weatherDataModelCaptor = ArgumentCaptor.forClass(WeatherDataModel.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        Mockito.when(responseWeatherGeneralModelMock.body()).thenReturn(weatherGeneralModelMock);

        Mockito.when(weatherGeneralModelMock.getName()).thenReturn("Dublin");

        MainModel mainModelMock = Mockito.mock(MainModel.class);
        Mockito.when(mainModelMock.getTemp()).thenReturn("30");
        Mockito.when(mainModelMock.getPressure()).thenReturn("111");
        Mockito.when(mainModelMock.getHumidity()).thenReturn("10");
        Mockito.when(weatherGeneralModelMock.getMain()).thenReturn(mainModelMock);

        WeatherModel weatherModelMock = Mockito.mock(WeatherModel.class);
        List<WeatherModel> listWeatherMock = Mockito.mock(List.class);
        Mockito.when(listWeatherMock.get(0)).thenReturn(weatherModelMock);
        Mockito.when(listWeatherMock.get(0).getIcon()).thenReturn("cloudn.png");
        Mockito.when(listWeatherMock.get(0).getDescription()).thenReturn("broken clouds");
        Mockito.when(weatherGeneralModelMock.getWeather()).thenReturn(listWeatherMock);

        SysModel sysModelMock = Mockito.mock(SysModel.class);
        Mockito.when(sysModelMock.getCountry()).thenReturn("IE");
        Mockito.when(weatherGeneralModelMock.getSys()).thenReturn(sysModelMock);

        WindModel windModelMock = Mockito.mock(WindModel.class);
        Mockito.when(windModelMock.getSpeed()).thenReturn("600");
        Mockito.when(weatherGeneralModelMock.getWind()).thenReturn(windModelMock);

        CloudsModel cloudsModelMock = Mockito.mock(CloudsModel.class);
        Mockito.when(cloudsModelMock.getAll()).thenReturn("100");
        Mockito.when(weatherGeneralModelMock.getClouds()).thenReturn(cloudsModelMock);

        retrofitCallback.onResponse(callGeneralModelMock, responseWeatherGeneralModelMock);

        Mockito.verify(myCallback)
                .onGetWeatherByCityNameSuccess((WeatherDataModel) weatherDataModelCaptor.capture());

        WeatherDataModel dataModel = (WeatherDataModel) weatherDataModelCaptor.getValue();
        Truth.assertThat(dataModel.getCityName()).isEqualTo("Dublin");
        Truth.assertThat(dataModel.getClouds()).isEqualTo("100%");
        Truth.assertThat(dataModel.getCountry()).isEqualTo("IE");
        Truth.assertThat(dataModel.getDescription()).isEqualTo("broken clouds");
        Truth.assertThat(dataModel.getHumidity()).isEqualTo("10%");
        Truth.assertThat(dataModel.getPressure()).isEqualTo("111 hpa");
        Truth.assertThat(dataModel.getWind()).isEqualTo("600 m/s");
        Truth.assertThat(dataModel.getWeatherTemp()).isEqualTo("30");
    }

    @Test
    public void checkOnGetWeatherByCoordinates_onFailure() {
        // Call callback's method

        interactor.getWeatherByCoordinates("35", "139");
        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        retrofitCallback.onFailure(callGeneralModelMock, Mockito.mock(Throwable.class));
    }

    @Test
    public void checkOnGetWeatherByCoordinates_onSuccessCallOnGetWeatherByCityNameError() {
        // Call callback's method

        interactor.getWeatherByCoordinates("35", "139");
        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        retrofitCallback.onResponse(callGeneralModelMock, responseWeatherGeneralModelMock);

        Mockito.verify(myCallback).onGetWeatherByCityNameError(Mockito.anyString());
    }

    @Test
    public void checkOnGetWeatherByCoordinates_onSuccessCallOnGetWeatherByCityNameSuccess(){
        // Call callback's method

        interactor.getWeatherByCoordinates("35", "139");
        ArgumentCaptor retrofitCallbackCaptor = ArgumentCaptor.forClass(Callback.class);
        ArgumentCaptor weatherDataModelCaptor = ArgumentCaptor.forClass(WeatherDataModel.class);

        Mockito.verify(callGeneralModelMock)
                .enqueue((Callback<WeatherGeneralModel>) retrofitCallbackCaptor.capture());

        Callback<WeatherGeneralModel> retrofitCallback =
                (Callback<WeatherGeneralModel>) retrofitCallbackCaptor.getValue();

        Mockito.when(responseWeatherGeneralModelMock.body()).thenReturn(weatherGeneralModelMock);

        Mockito.when(weatherGeneralModelMock.getName()).thenReturn("Dublin");

        MainModel mainModelMock = Mockito.mock(MainModel.class);
        Mockito.when(mainModelMock.getTemp()).thenReturn("30");
        Mockito.when(mainModelMock.getPressure()).thenReturn("111");
        Mockito.when(mainModelMock.getHumidity()).thenReturn("10");
        Mockito.when(weatherGeneralModelMock.getMain()).thenReturn(mainModelMock);

        WeatherModel weatherModelMock = Mockito.mock(WeatherModel.class);
        List<WeatherModel> listWeatherMock = Mockito.mock(List.class);
        Mockito.when(listWeatherMock.get(0)).thenReturn(weatherModelMock);
        Mockito.when(listWeatherMock.get(0).getIcon()).thenReturn("cloudn.png");
        Mockito.when(listWeatherMock.get(0).getDescription()).thenReturn("broken clouds");
        Mockito.when(weatherGeneralModelMock.getWeather()).thenReturn(listWeatherMock);

        SysModel sysModelMock = Mockito.mock(SysModel.class);
        Mockito.when(sysModelMock.getCountry()).thenReturn("IE");
        Mockito.when(weatherGeneralModelMock.getSys()).thenReturn(sysModelMock);

        WindModel windModelMock = Mockito.mock(WindModel.class);
        Mockito.when(windModelMock.getSpeed()).thenReturn("600");
        Mockito.when(weatherGeneralModelMock.getWind()).thenReturn(windModelMock);

        CloudsModel cloudsModelMock = Mockito.mock(CloudsModel.class);
        Mockito.when(cloudsModelMock.getAll()).thenReturn("100");
        Mockito.when(weatherGeneralModelMock.getClouds()).thenReturn(cloudsModelMock);

        retrofitCallback.onResponse(callGeneralModelMock, responseWeatherGeneralModelMock);

        Mockito.verify(myCallback)
                .onGetWeatherByCityNameSuccess((WeatherDataModel) weatherDataModelCaptor.capture());

        WeatherDataModel dataModel = (WeatherDataModel) weatherDataModelCaptor.getValue();
        Truth.assertThat(dataModel.getCityName()).isEqualTo("Dublin");
        Truth.assertThat(dataModel.getClouds()).isEqualTo("100%");
        Truth.assertThat(dataModel.getCountry()).isEqualTo("IE");
        Truth.assertThat(dataModel.getDescription()).isEqualTo("broken clouds");
        Truth.assertThat(dataModel.getHumidity()).isEqualTo("10%");
        Truth.assertThat(dataModel.getPressure()).isEqualTo("111 hpa");
        Truth.assertThat(dataModel.getWind()).isEqualTo("600 m/s");
        Truth.assertThat(dataModel.getWeatherTemp()).isEqualTo("30");
    }
}
