package com.example.airpollution.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.airpollution.Model.Location;

import java.util.ArrayList;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";

    public static final String DATABASE_NAME = "AirPollution";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_location";
    public static final String COL_ID = "col_id";
    public static final String COL_NAME = "col_name";
    public static final String COL_LATITUTE = "col_latitute";
    public static final String COL_LONGITUDE = "col_longitude";
    public static final String SQL_COMMAND_CREATE_POST_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT," +
            COL_LATITUTE + " FLOAT , " +
            COL_LONGITUDE + " FLOAT);";
    private Context context;

    public DatabaseOpenHelper(@Nullable Context context) {
        super( context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL( SQL_COMMAND_CREATE_POST_TABLE );
        } catch (SQLException e) {
            Log.e( TAG, "onCreate: " + e.toString() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addLocation(Location location){
        ContentValues cv=new ContentValues(  );
        cv.put( COL_NAME, location.getName() );
        cv.put( COL_LATITUTE, location.getLatitute() );
        cv.put( COL_LONGITUDE, location.getLongitude() );

        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        long isInserted= sqLiteDatabase.insert( TABLE_NAME,null,cv );
        if(isInserted>0){
            return true;
        }else return false;
    }
    public void addLocations(ArrayList<Location> locations){
        for (int i=0; i<locations.size();i++){
            addLocation( locations.get( i ) );
        }
    }


}
