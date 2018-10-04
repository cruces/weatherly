package com.weatherly.weatherly.modules.about.core.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.weatherly.weatherly.R;

public class DefaultAboutView extends FrameLayout implements AboutView {
    private Toolbar toolbar;

    public DefaultAboutView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_about, this);

        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.color_transparent_gray));
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }
}
