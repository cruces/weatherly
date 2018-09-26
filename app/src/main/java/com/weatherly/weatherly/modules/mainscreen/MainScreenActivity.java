package com.weatherly.weatherly.modules.mainscreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.weatherly.weatherly.application.MyApplication;
import com.weatherly.weatherly.modules.common.ThemeUtils;
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

        ThemeUtils.setAppTheme(this);
        MyApplication.getInjector().inject(this);

        setContentView(view.getView());

        presenter.onCreate();
        setSupportActionBar(view.getToolbar());
        view.setStatusBarColor(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        view.setMainMenu(this, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        presenter.onCheckPermissions(requestCode, grantResults);
    }
}
