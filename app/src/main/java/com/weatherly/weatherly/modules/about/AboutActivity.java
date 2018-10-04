package com.weatherly.weatherly.modules.about;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.about.core.presenter.AboutPresenter;
import com.weatherly.weatherly.modules.about.core.view.AboutView;
import com.weatherly.weatherly.modules.common.ThemeUtils;

import javax.inject.Inject;

public class AboutActivity extends AppCompatActivity {
    @Inject
    public AboutPresenter presenter;

    @Inject
    public AboutView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeUtils.setAppTheme(this);
        MyApplication.getInjector().injectAbout(this);

        setContentView(view.getView());

        presenter.onCreate();

        setSupportActionBar(view.getToolbar());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        view.setStatusBarColor(this);
    }
}
