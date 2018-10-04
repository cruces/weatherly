package com.weatherly.weatherly.modules.about.core.view;

import android.app.Activity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public interface AboutView {
    View getView();

    void setStatusBarColor(Activity activity);

    Toolbar getToolbar();
}
