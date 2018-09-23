package com.weatherly.weatherly.modules.todayforecast.core.view.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.weatherly.weatherly.R;

public class ForecastHeaderViewHolder extends RecyclerView.ViewHolder {
    private TextView header;

    public ForecastHeaderViewHolder(View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.header);
    }

}
