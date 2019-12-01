package com.example.airpollution.ExternalService;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.airpollution.Model.AirPollution;
import com.example.airpollution.Model.Location;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AirPollutionApiService {
    private static final String TAG = "AirPollutionApiService";
    public Context context;

    public AirPollutionApiService(Context context) {
        this.context = context;
    }

    public void getCityAirPollution(Location location, final onCityAirPollutionReceived onCityAirPollutionReceived) {
        JsonObjectRequest jsonObjectPollutionRequest = new JsonObjectRequest( Request.Method.GET,
                "http://api.airvisual.com/v2/city?city=" + location.getName() + "&state="
                        + (location.getState()) + "&country=Iran&key=660549f8-bdc5-481d-a320-b9cc6ccf4c30", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        onCityAirPollutionReceived.onReceive( parseResponseToAirpollution( response ) );
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                int x=5;
            }
        }
        );
        jsonObjectPollutionRequest.setRetryPolicy( new DefaultRetryPolicy( 8000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ) );

        RequestQueue requestQueue = Volley.newRequestQueue( context );
        requestQueue.add( jsonObjectPollutionRequest );
    }

    public interface onCityAirPollutionReceived {
        void onReceive(AirPollution airPollution);
    }

    private AirPollution parseResponseToAirpollution(JSONObject response) {
        AirPollution airPollution = new AirPollution();
        try {
            JSONObject cityJsonObject = response.getJSONObject( "data" );
            airPollution.setCityName( cityJsonObject.getString( "city" ) );

            JSONObject weatherJsonObject = response.getJSONObject( "data" ).getJSONObject( "current" ).getJSONObject( "weather" );
            airPollution.setPr( (float) weatherJsonObject.getDouble( "pr" ) );
            airPollution.setHu( (float) weatherJsonObject.getDouble( "hu" ) );
            airPollution.setTp( (float) weatherJsonObject.getDouble( "tp" ) );
            airPollution.setWd( (float) weatherJsonObject.getDouble( "wd" ) );

            JSONObject pollutionJsonObject = response.getJSONObject( "data" ).getJSONObject( "current" ).getJSONObject( "pollution" );
            airPollution.setPollution( (float) pollutionJsonObject.getDouble( "aqius" ) );

            return airPollution;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
}
