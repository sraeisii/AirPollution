package com.example.airpollution.Repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;


import com.example.airpollution.Model.Location;

import java.util.ArrayList;
import java.util.List;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseOpenHelper";

    public static final String DATABASE_NAME = "AirPollution";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "tbl_location";
    public static final String COL_ID = "col_id";
    public static final String COL_NAME = "col_name";
    public static final String COL_FA_NAME = "col_fa_name";
    public static final String COL_STATE = "col_state";
    public static final String COL_LATITUTE = "col_latitute";
    public static final String COL_LONGITUDE = "col_longitude";
    public static final String SQL_COMMAND_CREATE_POST_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NAME + " TEXT," +
            COL_FA_NAME + " TEXT," +
            COL_STATE + " TEXT," +
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
            seedData(db);
        } catch (SQLException e) {
            Log.e( TAG, "onCreate: " + e.toString() );
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void seedData(SQLiteDatabase db){
        ContentValues cv=new ContentValues(  );
        cv.put( COL_NAME, "Zanjan" );
        cv.put( COL_FA_NAME,"زنجان");
        cv.put( COL_STATE,"Zanjan");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Karaj" );
        cv.put( COL_FA_NAME,"البرز");
        cv.put( COL_STATE,"Alborz");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Tehran" );
        cv.put( COL_FA_NAME,"تهران");
        cv.put( COL_STATE,"Tehran");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Shiraz" );
        cv.put( COL_FA_NAME,"شیراز");
        cv.put( COL_STATE,"Fars");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Arak" );
        cv.put( COL_FA_NAME,"اراک");
        cv.put( COL_STATE,"Markazi");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Gorgan" );
        cv.put( COL_FA_NAME,"گرگان");
        cv.put( COL_STATE,"Golestan");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Qazvin" );
        cv.put( COL_FA_NAME,"قزوین");
        cv.put( COL_STATE,"Qazvin");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Qom" );
        cv.put( COL_FA_NAME,"قم");
        cv.put( COL_STATE,"Qom");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Rasht" );
        cv.put( COL_FA_NAME,"رشت");
        cv.put( COL_STATE,"Gilan");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );
        cv.clear();
        cv=new ContentValues(  );
        cv.put( COL_NAME, "Mashhad" );
        cv.put( COL_FA_NAME,"مشهد");
        cv.put( COL_STATE,"Razavi Khorasan");
        cv.put( COL_LATITUTE, 0.0 );
        cv.put( COL_LONGITUDE, 0.0);
        db.insert( TABLE_NAME,null,cv );

    }

    public ArrayList<Location> getLocations(){
        ArrayList<Location> locations = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT * FROM " + TABLE_NAME + " Order By " + COL_NAME,  null );
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                Location location = new Location();
                location.setId( cursor.getInt( 0 ) );
                location.setName( cursor.getString( 1 ) );
                location.setFaName( cursor.getString( 2 ) );
                location.setState( cursor.getString( 3 ) );
                location.setLatitute( cursor.getFloat( 4 ) );
                location.setLongitude( cursor.getFloat( 5 ) );
                locations.add( location );
                cursor.moveToNext();
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return locations;
    }

    public String getCityFaName(String cityName){
        String cityFaName="";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery( "SELECT "+ COL_FA_NAME+" FROM "+ TABLE_NAME + " Where "+ COL_NAME +" = '"+ cityName + "'",  null );
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            while (!cursor.isAfterLast()) {
                try {
                    cityFaName=cursor.getString( 0);
                    cursor.moveToNext();
                }
                catch (Exception e){
                    int x=5;
                }

            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return cityFaName;
    }

}
