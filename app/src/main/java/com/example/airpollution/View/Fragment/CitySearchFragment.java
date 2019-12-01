package com.example.airpollution.View.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.airpollution.Adapter.CitySearchListAdapter;
import com.example.airpollution.ExternalService.AirPollutionApiService;
import com.example.airpollution.Model.AirPollution;
import com.example.airpollution.Model.Location;
import com.example.airpollution.R;
import com.example.airpollution.Repository.DatabaseOpenHelper;

import java.util.ArrayList;

public class CitySearchFragment extends Fragment implements AirPollutionApiService.onCityAirPollutionReceived{
    private RecyclerView citySearchRV;
    private ArrayList<AirPollution> airPollutions = new ArrayList<>(  );
    private CitySearchListAdapter citySearchListAdapter = null;
    DatabaseOpenHelper databaseOpenHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_city_search, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        findViews(view);
        databaseOpenHelper = new DatabaseOpenHelper( getContext());
        ArrayList<Location> locations= databaseOpenHelper.getLocations();
        AirPollutionApiService airPollutionApiService = new AirPollutionApiService(getContext());
        initRecyclerView();

        for(int i=0;i<locations.size();i++){
            airPollutionApiService.getCityAirPollution( locations.get( i ), this );
        }
    }

    private void initRecyclerView(){
        LinearLayoutManager linearLayout= new LinearLayoutManager( getContext() , LinearLayoutManager.VERTICAL,false );
        citySearchRV.setLayoutManager( linearLayout );

        citySearchListAdapter= new CitySearchListAdapter( getContext(), airPollutions);
        citySearchRV.setAdapter( citySearchListAdapter );
    }
    private void findViews(View view){
        citySearchRV=(RecyclerView)view.findViewById( R.id.city_search_rv );
    }

    @Override
    public void onReceive(AirPollution airPollution) {
        airPollutions.add( airPollution );
        airPollution.setCityFaName(databaseOpenHelper.getCityFaName( airPollution.getCityName()));
        citySearchListAdapter.notifyItemInserted(airPollutions.size() - 1);
    }
}
