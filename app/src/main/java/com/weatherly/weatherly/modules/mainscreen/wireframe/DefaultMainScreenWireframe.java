package com.weatherly.weatherly.modules.mainscreen.wireframe;

import android.content.Intent;

import com.weatherly.weatherly.modules.forecast.ForecastActivity;
import com.weatherly.weatherly.modules.mainscreen.MainScreenActivity;

public class DefaultMainScreenWireframe implements MainScreenWireframe {
    private MainScreenActivity activity;

    public DefaultMainScreenWireframe(MainScreenActivity activity) {
        this.activity = activity;
    }

    @Override
    public void openForecastScreen() {
        Intent intent = new Intent(activity, ForecastActivity.class);
        activity.startActivity(intent);
    }
}
