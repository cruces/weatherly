package com.weatherly.weatherly.modules.forecast.core.view.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

public class ForecastListAdapter extends RecyclerView.Adapter<ForecastListViewHolder> {
    private ArrayList<ForecastDataListModel> forecastList;

    public ForecastListAdapter(ArrayList<ForecastDataListModel> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public ForecastListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ForecastListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastListViewHolder holder, int position) {
        holder.setData(forecastList.get(position));
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
