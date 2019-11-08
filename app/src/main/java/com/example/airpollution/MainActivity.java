package com.example.airpollution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.airpollution.ExternalService.LocationApiService;
import com.example.airpollution.Model.Location;
import com.example.airpollution.Repository.DatabaseOpenHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LocationApiService.onCityLocationRecieved {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        LocationApiService locationApiService= new LocationApiService( this );
        locationApiService.getCityLocation( MainActivity.this );
    }

    @Override
    public void onRecieve(ArrayList<Location> locations) {

        DatabaseOpenHelper databaseOpenHelper = new DatabaseOpenHelper( this );
        databaseOpenHelper.addLocations( locations );

    }
}
