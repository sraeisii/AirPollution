package com.example.airpollution.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.example.airpollution.View.Activity.MainActivity;
import com.example.airpollution.View.Fragment.CitySearchFragment;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    public MainViewPagerAdapter(FragmentManager fm) {
        super( fm );
    }

    @Override
    public Fragment getItem(int position) {
        return new CitySearchFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        {
            switch (position){
                case 0: return "جستجو";

                case 1: return "نقشه";

                case 2: return "پروفایل";

                default: return "";
            }
        }
    }
}
