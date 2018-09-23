package com.weatherly.weatherly.modules.extendedforecast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.extendedforecast.core.presenter.ExtendedForecastPresenter;
import com.weatherly.weatherly.modules.extendedforecast.core.view.ExtendedForecastView;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

import javax.inject.Inject;

public class ExtendedForecastFragment extends Fragment {
    private OnSwipeToRefresh swipeToRefresh;

    @Inject
    ExtendedForecastView view;

    @Inject
    ExtendedForecastPresenter presenter;

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
        MyApplication.getInjector().injectTodayForecast(this);
        ArrayList<ForecastDataListModel> transactionList =
                (ArrayList<ForecastDataListModel>)getArguments().getSerializable("list");
        presenter.onCreate(transactionList);
        view.setOnSwipeToRefresh(swipeToRefresh);
    }

    public static ExtendedForecastFragment newInstance(ArrayList<ForecastDataListModel> forecastList) {
        ExtendedForecastFragment fragment = new ExtendedForecastFragment();
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
