package com.martinzarev.weatherapp.RecyclerViews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.martinzarev.weatherapp.DetailsActivity;
import com.martinzarev.weatherapp.Models.DailyForecast;
import com.martinzarev.weatherapp.R;

import java.util.ArrayList;

/**
 * Created by martin on 28.07.17.
 */

public class AllCitiesRecyclerView extends RecyclerView.Adapter<AllCitiesRecyclerView.AllViewHolder> {

    //The data for the RV
    private ArrayList<DailyForecast> dailyForecasts;

    //Context for inflater
    private Context context;

    public AllCitiesRecyclerView(ArrayList<DailyForecast> dailyForecasts, Context context){
        this.dailyForecasts = dailyForecasts;
        this.context = context;
    }

    @Override
    public AllCitiesRecyclerView.AllViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AllViewHolder(LayoutInflater.from(context).inflate(R.layout.city_rv_item,parent,false));
    }

    @Override
    public void onBindViewHolder(AllCitiesRecyclerView.AllViewHolder holder, int position) {
        final DailyForecast currentDailyForecast = dailyForecasts.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("detail_city", currentDailyForecast);

                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        holder.bindTo(currentDailyForecast);
    }

    @Override
    public int getItemCount() {
        return dailyForecasts.size();
    }

    class AllViewHolder extends RecyclerView.ViewHolder{

        private TextView cityNameTV, cityDegreesTV;

        public AllViewHolder(View itemView) {
            super(itemView);

            //Init the views
            cityNameTV = itemView.findViewById(R.id.city_rv_item_name);
            cityDegreesTV = itemView.findViewById(R.id.city_rv_item_degree);

        }

        void bindTo(final DailyForecast currentDailyForecast){
            cityNameTV.setText(currentDailyForecast.getName());
            cityDegreesTV.setText(String.valueOf(currentDailyForecast.getMain().getTemp()));
        }
    }

    public ArrayList<Integer> getAllIds(){
        ArrayList<Integer> allIds = new ArrayList<>();

        for(DailyForecast tempDailyForcast : dailyForecasts){
            allIds.add(tempDailyForcast.getId());
        }

        return allIds;
    }

    public void addData(DailyForecast forecast){
        dailyForecasts.add(forecast);
        notifyDataSetChanged();
    }

    public void addDataAtPosition( int position, DailyForecast forecast){
        dailyForecasts.add(position,forecast);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<DailyForecast> forecasts){
        for(DailyForecast tempDailyForecast : forecasts){
            addData(tempDailyForecast);
        }
    }

    public void eraseData(){
        dailyForecasts.clear();
    }

    public DailyForecast eraseAtPosition(int position){
        DailyForecast tempDailyForecast = dailyForecasts.get(position);
        dailyForecasts.remove(position);
        notifyDataSetChanged();
        return tempDailyForecast;
    }

    public ArrayList<DailyForecast> getAllData(){
        return dailyForecasts;
    }

}
