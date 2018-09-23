package com.weatherly.weatherly.modules.todayforecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.todayforecast.core.presenter.TodayForecastPresenter;
import com.weatherly.weatherly.modules.todayforecast.core.view.TodayForecastView;

import java.util.ArrayList;

import javax.inject.Inject;

public class TodayForecastFragment extends Fragment {
    private OnSwipeToRefresh swipeToRefresh;

    @Inject
    TodayForecastView view;

    @Inject
    TodayForecastPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return view.getView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //injectan
        MyApplication.getInjector().injectTodayForecast(this);
        ArrayList<ForecastDataListModel> transactionList = (ArrayList<ForecastDataListModel>)getArguments().getSerializable("list");
        Log.d("dume", "la lista en el fragment" + transactionList.size());
        presenter.onCreate(transactionList);
        view.setOnSwipeToRefresh(swipeToRefresh);
//        getArguments().getString("keyDeAlgo");
    }

    public static TodayForecastFragment newInstance(ArrayList<ForecastDataListModel> forecastList) {
        TodayForecastFragment fragment = new TodayForecastFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("list", forecastList);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setOnSwipeToRefresh(OnSwipeToRefresh onSwipeToRefresh) {
        this.swipeToRefresh = onSwipeToRefresh;
    }

    public interface OnSwipeToRefresh {
        void onSwiped();
    }
}
