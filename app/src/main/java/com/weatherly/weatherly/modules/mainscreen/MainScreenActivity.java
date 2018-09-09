package com.weatherly.weatherly.modules.mainscreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.mainscreen.core.presenter.MainScreenPresenter;
import com.weatherly.weatherly.modules.mainscreen.core.view.MainScreenView;

import javax.inject.Inject;

public class MainScreenActivity extends AppCompatActivity {
    @Inject
    public MainScreenPresenter presenter;

    @Inject
    public MainScreenView view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication.getInjector().inject(this);

        setContentView(view.getView());

        presenter.onCreate();
        setSupportActionBar(view.getToolbar());
        view.setStatusBarColor(this);

    }
}
