package com.example.airpollution.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.airpollution.Model.AirPollution;
import com.example.airpollution.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CitySearchListAdapter extends RecyclerView.Adapter<CitySearchListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<AirPollution> airpollutions;
    Typeface iranianSansFont;
    Typeface mitraFont;

    public CitySearchListAdapter(Context context, ArrayList<AirPollution> airpollutions) {
        this.context = context;
        this.airpollutions = airpollutions;
        iranianSansFont =Typeface.createFromAsset(context.getAssets(),"fonts/iranian_sans.ttf");
        mitraFont =Typeface.createFromAsset(context.getAssets(),"fonts/far_mitra.ttf");
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
        if(cityPollution <= 50){
            holder.cityPollutionLevelImg.setBackgroundResource(R.drawable.ic_face_green );
            holder.citySerachCv.setCardBackgroundColor( Color.parseColor("#A8E05F"));
        }
        else if(cityPollution >50 && cityPollution <= 100 ){
            holder.cityPollutionLevelImg.setBackgroundResource(R.drawable.ic_face_yellow );
            holder.citySerachCv.setCardBackgroundColor( Color.parseColor("#FDD74B"));
        }
        else if(cityPollution >100 && cityPollution <= 150 ){
            holder.cityPollutionLevelImg.setBackgroundResource(R.drawable.ic_face_red );
            holder.citySerachCv.setCardBackgroundColor( Color.parseColor("#FE6A69"));
        }
        else {
            holder.cityPollutionLevelImg.setBackgroundResource(R.drawable.ic_face_purple );
            holder.citySerachCv.setCardBackgroundColor( Color.parseColor("#A87DC0"));
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
        private CardView citySerachCv;
        private ImageView cityPollutionLevelImg;
        private TextView cityName;
        private TextView cityPollutionRateTxt;
        private TextView cityTpTxt;
        private TextView cityHuTxt;
        private TextView cityPrTxt;
        private TextView cityWindTxt;
        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            citySerachCv=(CardView) itemView.findViewById( R.id.city_serach_cv ) ;
            cityPollutionLevelImg=(ImageView)itemView.findViewById( R.id.city_pollution_level_img );
            cityName=(TextView)itemView.findViewById(R.id.city_name_txt );
            cityName.setTypeface(mitraFont  );
            cityPollutionRateTxt=(TextView)itemView.findViewById(R.id.city_pollution_rate_txt );
            cityPollutionRateTxt.setTypeface(  iranianSansFont);
            cityTpTxt=(TextView)itemView.findViewById(R.id.city_tp_txt );
            cityTpTxt.setTypeface( iranianSansFont );
            cityHuTxt=(TextView)itemView.findViewById(R.id.city_hu_txt );
            cityHuTxt.setTypeface( iranianSansFont );
            cityPrTxt=(TextView)itemView.findViewById(R.id.city_pr_txt );
            cityPrTxt.setTypeface( iranianSansFont );
            cityWindTxt=(TextView)itemView.findViewById(R.id.city_wind_txt );
            cityWindTxt.setTypeface( iranianSansFont );

        }
    }
}
