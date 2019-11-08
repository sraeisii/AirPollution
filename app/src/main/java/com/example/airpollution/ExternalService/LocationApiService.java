package com.example.airpollution.ExternalService;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.airpollution.Model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LocationApiService {
    private static final String TAG = "LocationApiService";
    public Context context;

    public LocationApiService(Context context) {
        this.context = context;
    }

    public void getCityLocation(final onCityLocationRecieved onCityLocationRecieved){
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest( Request.Method.GET,
                "http://81.31.168.232:8000/api/geography/getByType?geographyType=City&pageNumber=1&pageSize=10",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i( TAG, "onResponse:"+ response.toString() );
                        onCityLocationRecieved.onRecieve( parseResponseToLocation(response) );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e( TAG, "onErrorResponse: "+ error.toString() );
                onCityLocationRecieved.onRecieve( null );
            }
        } );

        jsonObjectRequest.setRetryPolicy( new DefaultRetryPolicy( 8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) );

        RequestQueue requestQueue = Volley.newRequestQueue( context );
        requestQueue.add( jsonObjectRequest );

    }

    public interface onCityLocationRecieved{
        void onRecieve(ArrayList<Location> locations);
    }

    private ArrayList<Location> parseResponseToLocation(JSONObject response){
        ArrayList<Location> locations= new ArrayList<>();
        try {
            JSONArray locationJsonArray= response.getJSONArray( "result" );
            for(int i=0;i<locationJsonArray.length();i++){
                Location location= new Location();
                JSONObject locationJsonObject= locationJsonArray.getJSONObject( i );
                location.setName( locationJsonObject.getString( "titleFa" ) );
                location.setLatitute((float) locationJsonObject.getDouble( "latitude" ) );
                location.setLongitude( (float) locationJsonObject.getDouble( "longitude" ) );

                locations.add( location );
            }

            return locations;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }



    }
}
