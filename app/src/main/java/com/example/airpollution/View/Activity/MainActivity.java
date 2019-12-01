package com.example.airpollution.View.Activity;

import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import com.example.airpollution.Adapter.CitySearchListAdapter;
import com.example.airpollution.Adapter.MainViewPagerAdapter;
import com.example.airpollution.ExternalService.AirPollutionApiService;
import com.example.airpollution.ExternalService.LocationApiService;
import com.example.airpollution.Model.AirPollution;
import com.example.airpollution.Model.Location;
import com.example.airpollution.R;
import com.example.airpollution.Repository.DatabaseOpenHelper;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "MainActivity";

    private TabLayout mainTabLayout;
    private ViewPager mainViewPager;
    private Toolbar mainToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        findViews();
        configureViews();
        setupToolbar();

    }


    private void findViews(){
        mainTabLayout=(TabLayout)findViewById( R.id.main_tab_layout );
        mainViewPager=(ViewPager) findViewById( R.id.main_view_pager );
        mainToolbar = (Toolbar) findViewById( R.id.main_toolabar );

    }
    private void configureViews(){
        MainViewPagerAdapter mainViewPagerAdapter= new MainViewPagerAdapter( getSupportFragmentManager() );
        mainViewPager.setAdapter( mainViewPagerAdapter );
        mainTabLayout.setupWithViewPager( mainViewPager );
    }
    private void setupToolbar(){
        setSupportActionBar( mainToolbar );
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( true );
        actionBar.setHomeButtonEnabled( true );
    }


}
