package com.weatherly.weatherly.modules.common;

import com.weatherly.weatherly.modules.common.entities.ForecastGeneralModel;
import com.weatherly.weatherly.modules.common.entities.WeatherGeneralModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRequest {

    @GET("weather")
    Call<WeatherGeneralModel> getByCityName(@Query("q") String city,
                                            @Query("units") String value,
                                            @Query("appid") String key);

    @GET("forecast")
    Call<ForecastGeneralModel> getForecastNextDays(@Query("q") String city,
                                                   @Query("units") String value,
                                                   @Query("appid") String key);

}
