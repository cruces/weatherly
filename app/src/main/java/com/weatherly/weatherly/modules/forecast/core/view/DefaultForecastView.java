package com.weatherly.weatherly.modules.forecast.core.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.forecast.core.view.recyclerview.ForecastListAdapter;

import java.util.ArrayList;

public class DefaultForecastView extends FrameLayout implements ForecastView {
    private RecyclerView recyclerViewForecast;
    private ForecastViewOutput callbacks;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private View overlay;
    private Context context;
    private SwipeRefreshLayout swipeRefreshLayout;

    public DefaultForecastView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_forecast, this);
        this.context = context;

        recyclerViewForecast = findViewById(R.id.recycler_forecast_list);
        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_bar);
        overlay = findViewById(R.id.overlay_view);
        swipeRefreshLayout = findViewById(R.id.swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                callbacks.onSwipeRefresh();
            }
        });
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
    public void setCallbacks(ForecastViewOutput callback) {
        this.callbacks = callback;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.color_transparent_gray));
    }

    @Override
    public void setUpToolbar(ForecastDataModel forecast) {
        toolbar.setTitle(forecast.getCity() + ", " + forecast.getCountry());
    }

    @Override
    public void setProgressBar(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
        }
    }

    @Override
    public void getToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
