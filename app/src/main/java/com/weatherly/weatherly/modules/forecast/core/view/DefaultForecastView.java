package com.weatherly.weatherly.modules.forecast.core.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.FrameLayout;

import com.weatherly.weatherly.R;

public class DefaultForecastView extends FrameLayout implements ForecastView {
    public DefaultForecastView(@NonNull Context context) {
        super(context);

        inflate(context, R.layout.activity_forecast, this);
    }

    @Override
    public View getView() {
        return this;
    }
}
