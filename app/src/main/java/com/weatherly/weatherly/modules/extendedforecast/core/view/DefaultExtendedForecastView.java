package com.weatherly.weatherly.modules.extendedforecast.core.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.extendedforecast.core.view.recyclerview.ForecastListAdapter;

import java.util.ArrayList;

public class DefaultExtendedForecastView extends FrameLayout implements ExtendedForecastView {
    private RecyclerView recyclerViewForecast;
    private ExtendedForecastFragment.OnSwipeToRefresh swipeToRefresh;
    private SwipeRefreshLayout swipeRefreshLayout;

    public DefaultExtendedForecastView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.fragment_extended_forecast, this);

        recyclerViewForecast = findViewById(R.id.recycler_forecast_list);
        swipeRefreshLayout = findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (swipeToRefresh != null) {
                    swipeRefreshLayout.setRefreshing(false);
                    swipeToRefresh.onSwiped();
                }
            }
        });
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setUpForecastList(ArrayList<ForecastDataListModel> list) {
        ForecastListAdapter forecastListAdapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewForecast.setLayoutManager(layoutManager);
        forecastListAdapter = new ForecastListAdapter(list);
        recyclerViewForecast.setAdapter(forecastListAdapter);
        recyclerViewForecast.setNestedScrollingEnabled(false);
    }

    @Override
    public void setOnSwipeToRefresh(ExtendedForecastFragment.OnSwipeToRefresh swipeToRefresh) {
        this.swipeToRefresh = swipeToRefresh;
    }
}
