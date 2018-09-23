package com.weatherly.weatherly.modules.forecast.core.view.adapters;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.weatherly.weatherly.modules.extendedforecast.ExtendedForecastFragment;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

public class ForecastPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ArrayList<ForecastDataListModel>> data = new ArrayList<>();
    private ExtendedForecastFragment.OnSwipeToRefresh swipeToRefresh;

    public ForecastPagerAdapter(FragmentManager fm, ExtendedForecastFragment.OnSwipeToRefresh swipeToRefresh) {
        super(fm);
        this.swipeToRefresh = swipeToRefresh;
    }

    @Override
    public Fragment getItem(int position) {
        ExtendedForecastFragment fragment = ExtendedForecastFragment.newInstance(data.get(position));
        fragment.setOnSwipeToRefresh(swipeToRefresh);
        return fragment;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    public void setData(ArrayList<ArrayList<ForecastDataListModel>> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
