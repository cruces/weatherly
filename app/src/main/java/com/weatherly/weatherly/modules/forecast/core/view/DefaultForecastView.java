package com.weatherly.weatherly.modules.forecast.core.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.view.recyclerview.ForecastListAdapter;

import java.util.ArrayList;

public class DefaultForecastView extends FrameLayout implements ForecastView {
    private RecyclerView recyclerViewForecast;
    private ForecastListAdapter forecastListAdapter;
    private ForecastViewOutput callbacks;

    public DefaultForecastView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_forecast, this);
        recyclerViewForecast = findViewById(R.id.recycler_forecast_list);
    }

    @Override
    public void setUpForecastList(ArrayList<ForecastDataListModel> list) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewForecast.setLayoutManager(layoutManager);
        forecastListAdapter = new ForecastListAdapter(list);
        recyclerViewForecast.setAdapter(forecastListAdapter);
    }

    @Override
    public void setCallbacks(ForecastViewOutput callback) {
        this.callbacks = callback;
    }

    @Override
    public View getView() {
        return this;
    }
}
