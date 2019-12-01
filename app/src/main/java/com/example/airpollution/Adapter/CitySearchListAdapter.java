package com.example.airpollution.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airpollution.Model.AirPollution;
import com.example.airpollution.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CitySearchListAdapter extends RecyclerView.Adapter<CitySearchListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AirPollution> airpollutions;

    public CitySearchListAdapter(Context context, ArrayList<AirPollution> airpollutions) {
        this.context = context;
        this.airpollutions = airpollutions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view= LayoutInflater.from( context).inflate( R.layout.layout_city_search, parent, false);
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        float cityPollution= airpollutions.get( position ).getPollution();
        if(cityPollution >=50 && cityPollution <= 100 ){
            holder.cityPollutionLevelImg.setBackgroundResource(R.drawable.ic_face_red );
        }

        holder.cityName.setText( airpollutions.get( position ).getCityFaName() );
        holder.cityPollutionRateTxt.setText( String.valueOf( airpollutions.get( position ).getPollution()  ));
        holder.cityTpTxt.setText(String.valueOf( airpollutions.get( position ).getTp() ));
        holder.cityPrTxt.setText( String.valueOf( airpollutions.get( position ).getPr() ) );
        holder.cityHuTxt.setText(String.valueOf( airpollutions.get( position ).getHu() )  );
        holder.cityWindTxt.setText( String.valueOf( airpollutions.get( position ).getWd()  ));

    }

    @Override
    public int getItemCount() {
        return airpollutions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView cityPollutionLevelImg;
        private TextView cityName;
        private TextView cityPollutionRateTxt;
        private TextView cityTpTxt;
        private TextView cityHuTxt;
        private TextView cityPrTxt;
        private TextView cityWindTxt;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            cityPollutionLevelImg=(ImageView)itemView.findViewById( R.id.city_pollution_level_img );
            cityName=(TextView)itemView.findViewById(R.id.city_name_txt );
            cityPollutionRateTxt=(TextView)itemView.findViewById(R.id.city_pollution_rate_txt );
            cityTpTxt=(TextView)itemView.findViewById(R.id.city_tp_txt );
            cityHuTxt=(TextView)itemView.findViewById(R.id.city_hu_txt );
            cityPrTxt=(TextView)itemView.findViewById(R.id.city_pr_txt );
            cityWindTxt=(TextView)itemView.findViewById(R.id.city_wind_txt );

        }
    }
}
