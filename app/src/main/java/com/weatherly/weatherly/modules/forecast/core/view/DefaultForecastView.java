package com.weatherly.weatherly.modules.forecast.core.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.weatherly.weatherly.R;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataListModel;
import com.weatherly.weatherly.modules.forecast.core.entities.ForecastDataModel;
import com.weatherly.weatherly.modules.forecast.core.view.adapters.ForecastPagerAdapter;
import com.weatherly.weatherly.modules.todayforecast.TodayForecastFragment;
import com.weatherly.weatherly.modules.todayforecast.core.view.recyclerview.ForecastListAdapter;

import java.util.ArrayList;

public class DefaultForecastView extends FrameLayout implements ForecastView {
    private ForecastViewOutput callbacks;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private View overlay;
    private Context context;
//    private SwipeRefreshLayout swipeRefreshLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ForecastPagerAdapter adapter;

    public DefaultForecastView(@NonNull Context context) {
        super(context);
        inflate(context, R.layout.activity_forecast, this);
        this.context = context;

        toolbar = findViewById(R.id.toolbar);
        progressBar = findViewById(R.id.progress_bar);
        overlay = findViewById(R.id.overlay_view);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.forecast_pager);

    }

    @Override
    public void setUpTabs(AppCompatActivity activity) {
        tabLayout.addTab(tabLayout.newTab().setText("Today"));
        tabLayout.addTab(tabLayout.newTab().setText("Tomorrow"));

        adapter = new ForecastPagerAdapter(
                activity.getSupportFragmentManager(), new TodayForecastFragment.OnSwipeToRefresh() {
            @Override
            public void onSwiped() {
                callbacks.onSwipeRefresh();
            }
        });
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }



    @Override
    public void setCallbacks(ForecastViewOutput callback) {
        this.callbacks = callback;
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(activity.getResources().getColor(R.color.color_transparent_gray));
    }

    @Override
    public void setUpToolbar(ForecastDataModel forecast) {
        toolbar.setTitle(forecast.getCity() + ", " + forecast.getCountry());
    }

    @Override
    public void setProgressBar(boolean status) {
        if (status) {
            progressBar.setVisibility(View.VISIBLE);
            overlay.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
            overlay.setVisibility(View.GONE);
        }
    }

    @Override
    public void getToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void updateViewPager(ArrayList<ArrayList<ForecastDataListModel>> list) {
        adapter.setData(list);
    }
}
