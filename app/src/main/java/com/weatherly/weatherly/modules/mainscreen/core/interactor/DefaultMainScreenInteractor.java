package com.weatherly.weatherly.modules.mainscreen.core.interactor;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.ActivityCompat;

import com.weatherly.weatherly.application.builder.MyApplicationModule;
import com.weatherly.weatherly.modules.common.WeatherRequest;
import com.weatherly.weatherly.modules.common.entities.WeatherGeneralModel;
import com.weatherly.weatherly.modules.common.openweathermap.TemperatureUtils;
import com.weatherly.weatherly.modules.mainscreen.core.entities.DeviceLocationModel;
import com.weatherly.weatherly.modules.mainscreen.core.entities.WeatherDataModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DefaultMainScreenInteractor implements MainScreenInteractor {
    private Retrofit retrofit;
    private WeatherRequest request;
    private MainScreenInteractorOutput callbacks;
    private Context context;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private LocationManager locationManager;
    private String latitude;
    private String longitude;

    public DefaultMainScreenInteractor(Context context, Retrofit retrofit) {
        this.retrofit = retrofit;
        this.context = context;
        request = retrofit.create(WeatherRequest.class);
    }

    @Override
    public void getWeatherByCityName() {
        request.getByCityName("Dublin", MyApplicationModule.UNITS, MyApplicationModule.USER_KEY)
                .enqueue(new Callback<WeatherGeneralModel>() {
            @Override
            public void onResponse(Call<WeatherGeneralModel> call, Response<WeatherGeneralModel> response) {
                if (response.body() != null) {

                    WeatherGeneralModel model = response.body();
                    WeatherDataModel dataModel = new WeatherDataModel(model.getName(),
                            TemperatureUtils.parseTemperature(model.getMain().getTemp()),
                            model.getWeather().get(0).getIcon(),
                            model.getMain().getPressure() + " hpa",
                            model.getMain().getHumidity() + "%",
                            model.getWind().getSpeed() + " m/s",
                            model.getWeather().get(0).getDescription(),
                            model.getSys().getCountry(),
                            model.getClouds().getAll() + "%");

                    callbacks.onGetWeatherByCityNameSuccess(dataModel);
                } else {
                    callbacks.onGetWeatherByCityNameError("error");
                }
            }

            @Override
            public void onFailure(Call<WeatherGeneralModel> call, Throwable t) {
                callbacks.onGetWeatherByCityNameError(t.toString());
            }
        });

    }

    @Override
    public void checkPermission(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getDeviceLocation();
                } else {
                    ActivityCompat.requestPermissions((Activity) context, new String[]{
                                    Manifest.permission.ACCESS_FINE_LOCATION,
                                    Manifest.permission.ACCESS_COARSE_LOCATION},
                            PERMISSION_REQUEST_CODE);
                }
            }
        }
    }

    @Override
    public void getWeatherByCoordinates(String lat, String lon) {
        request.getByCoordinates(lat, lon, MyApplicationModule.UNITS, MyApplicationModule.USER_KEY)
                .enqueue(new Callback<WeatherGeneralModel>() {
                    @Override
                    public void onResponse(Call<WeatherGeneralModel> call, Response<WeatherGeneralModel> response) {
                        if (response.body() != null) {

                            WeatherGeneralModel model = response.body();
                            WeatherDataModel dataModel = new WeatherDataModel(model.getName(),
                                    TemperatureUtils.parseTemperature(model.getMain().getTemp()),
                                    model.getWeather().get(0).getIcon(),
                                    model.getMain().getPressure() + " hpa",
                                    model.getMain().getHumidity() + "%",
                                    model.getWind().getSpeed() + " m/s",
                                    model.getWeather().get(0).getDescription(),
                                    model.getSys().getCountry(),
                                    model.getClouds().getAll() + "%");

                            callbacks.onGetWeatherByCityNameSuccess(dataModel);
                        } else {
                            callbacks.onGetWeatherByCityNameError("error");
                        }

                    }

                    @Override
                    public void onFailure(Call<WeatherGeneralModel> call, Throwable t) {
                        callbacks.onGetWeatherByCityNameError(t.toString());
                    }
                });
    }

    @Override
    public void getDeviceLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) context, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    PERMISSION_REQUEST_CODE);

        } else {
            locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            LocationListener locationListener = new LocationListener() {

                @Override
                public void onLocationChanged(Location location) {
                    locationManager.removeUpdates(this);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            };

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_COARSE);
            criteria.setPowerRequirement(Criteria.POWER_LOW);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setSpeedRequired(false);
            criteria.setCostAllowed(true);
            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);

            final Looper looper = null;
            locationManager.requestSingleUpdate(criteria, locationListener, looper);

            if (location != null) {
                longitude = Double.toString(location.getLongitude());
                latitude = Double.toString(location.getLatitude());
                getWeatherByCoordinates(latitude, longitude);
            }
        }


    }

    @Override
    public void setCallbacks(MainScreenInteractorOutput callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    public DeviceLocationModel getLocation() {
        return new DeviceLocationModel(latitude, longitude);
    }
}
