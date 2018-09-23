package com.weatherly.weatherly.modules.forecast.core.interactor;

import com.weatherly.weatherly.application.builder.MyApplicationModule;
import com.weatherly.weatherly.modules.common.WeatherRequest;
import com.weatherly.weatherly.modules.common.entities.ForecastGeneralModel;
import com.weatherly.weatherly.modules.common.entities.WeatherForecastModel;
import com.weatherly.weatherly.modules.common.openweathermap.DateUtils;
import com.weatherly.weatherly.modules.common.openweathermap.TemperatureUtils;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DefaultForecastInteractor implements ForecastInteractor {
    private Retrofit retrofit;
    private WeatherRequest request;
    private ForecastInteractorOutput callbacks;

    public DefaultForecastInteractor(Retrofit retrofit) {
        this.retrofit = retrofit;
        request = retrofit.create(WeatherRequest.class);
    }

    @Override
    public void getForecastList() {
        request.getForecastNextDays("Dublin", MyApplicationModule.UNITS,
                MyApplicationModule.USER_KEY).enqueue(new Callback<ForecastGeneralModel>() {
            @Override
            public void onResponse(Call<ForecastGeneralModel> call, Response<ForecastGeneralModel> response) {
                if (response.body() != null) {

                    ForecastGeneralModel model = response.body();
                    ArrayList<ForecastDataListModel> listForecast = new ArrayList<>();

                    for (int i = 0; i < model.getList().size(); i++) {
                        WeatherForecastModel forecastData = model.getList().get(i);
                        String dataDate = model.getList().get(i).getDt_txt();

                        listForecast.add(new ForecastDataListModel(
                                TemperatureUtils
                                        .parseTemperature(forecastData.getMain().getTemp()) + " °C",
                                DateUtils.convertDateShort(dataDate),
                                forecastData.getWeather().get(0).getIcon()));
                    }

                    ForecastDataModel dataModel = new ForecastDataModel(model.getCity().getName(),
                            model.getCity().getCountry());

                    ArrayList<ArrayList<ForecastDataListModel>> lists = new ArrayList<>();
                    lists.add(listForecast);
                    lists.add(listForecast);

                    callbacks.onGetForecastListSuccess(dataModel, lists);

                } else {
                    callbacks.onGetForecastListError("error");
                }
            }

            @Override
            public void onFailure(Call<ForecastGeneralModel> call, Throwable t) {
                callbacks.onGetForecastListError("error");
            }
        });
    }

    @Override
    public void setCallbacks(ForecastInteractorOutput callback) {
        this.callbacks = callback;
    }
}
