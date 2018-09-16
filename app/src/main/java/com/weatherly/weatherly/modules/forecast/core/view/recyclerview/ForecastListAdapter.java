package com.weatherly.weatherly.modules.forecast.core.view.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

import java.util.ArrayList;

public class ForecastListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ForecastDataListModel> forecastList;
    private int listHeaderType = 1;
    private int listItemType = 2;

    public ForecastListAdapter(ArrayList<ForecastDataListModel> forecastList) {
        this.forecastList = forecastList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == listItemType) {
            return new ForecastListViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forecast_item, parent, false));
        } else if (viewType == listHeaderType) {
            return new ForecastHeaderViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.forecast_header, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == listItemType)
            ((ForecastListViewHolder) holder).setData(forecastList.get(position - 1));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return listHeaderType;
        else
            return listItemType;
    }

    @Override
    public int getItemCount() {
        return forecastList.size() + 1;
    }
}
