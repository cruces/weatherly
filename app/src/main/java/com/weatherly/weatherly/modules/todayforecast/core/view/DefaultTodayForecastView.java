package com.weatherly.weatherly.modules.todayforecast.core.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;
import com.weatherly.weatherly.modules.todayforecast.core.view.recyclerview.ForecastListAdapter;

import java.util.ArrayList;

public class DefaultTodayForecastView extends FrameLayout implements TodayForecastView {
    private RecyclerView recyclerViewForecast;
    private TodayForecastFragment.OnSwipeToRefresh swipeToRefresh;
    private SwipeRefreshLayout swipeRefreshLayout;

    public DefaultTodayForecastView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.fragment_today_forecast, this);

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
        Log.d("dume", "la list en el fragment recycler" + list.size());

        ForecastListAdapter forecastListAdapter;
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewForecast.setLayoutManager(layoutManager);
        forecastListAdapter = new ForecastListAdapter(list);
        recyclerViewForecast.setAdapter(forecastListAdapter);
        recyclerViewForecast.setNestedScrollingEnabled(false);
    }

    @Override
    public void setOnSwipeToRefresh(TodayForecastFragment.OnSwipeToRefresh swipeToRefresh) {
        this.swipeToRefresh = swipeToRefresh;
    }
}
