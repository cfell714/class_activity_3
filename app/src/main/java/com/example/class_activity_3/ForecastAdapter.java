package com.example.class_activity_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<Forecast> forecasts;

    public ForecastAdapter(List<Forecast> forecasts){
        this.forecasts = forecasts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View forecastView = inflater.inflate(R.layout.item_forecast, parent, false);
        ViewHolder viewHolder = new ViewHolder(forecastView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Forecast forecast = forecasts.get(position);
        holder.textView_date_time.setText(forecast.getDate_time());
        holder.textView_weather_description.setText(forecast.getWeather_description());
        holder.textView_feels_like_temp.setText("Feels like " + forecast.getFeels_like_temp());

    }

    @Override
    public int getItemCount() {
        return forecasts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView_date_time;
        TextView textView_weather_description;
        TextView textView_feels_like_temp;

        public ViewHolder(View itemView){
            super(itemView);

            textView_date_time = itemView.findViewById(R.id.textView_date_time);
            textView_weather_description = itemView.findViewById(R.id.textView_weather_description);
            textView_feels_like_temp = itemView.findViewById(R.id.textView_feels_like_temp);

        }

    }

}
