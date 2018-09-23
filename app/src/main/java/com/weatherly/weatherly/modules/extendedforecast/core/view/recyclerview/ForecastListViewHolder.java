package com.weatherly.weatherly.modules.extendedforecast.core.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.common.openweathermap.IconUtils;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;

public class ForecastListViewHolder extends RecyclerView.ViewHolder {
    private TextView temp;
    private TextView date;
    private ImageView icon;
    private Context context;
    private TextView hour;

    public ForecastListViewHolder(View itemView) {
        super(itemView);
        temp = itemView.findViewById(R.id.temp);
        date = itemView.findViewById(R.id.date);
        icon = itemView.findViewById(R.id.weather_icon);
        hour = itemView.findViewById(R.id.hour);
        this.context = itemView.getContext();
    }

    public void setData(ForecastDataListModel model) {
        temp.setText(model.getTemp());
        date.setText(model.getDate());
        hour.setText(model.getHour());
        Glide.with(context).load(IconUtils.getWeatherIcon(context, model.getIcon()))
                .into(icon);
    }
}
