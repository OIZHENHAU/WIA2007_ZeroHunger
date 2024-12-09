package com.example.wia2007_zerohunger.Part1.service;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wia2007_zerohunger.R;
import com.example.wia2007_zerohunger.Part1.model.WeatherForecast;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder> {
    private ArrayList<WeatherForecast> weatherForecastList;
    private Context context;

    public WeatherForecastAdapter(ArrayList<WeatherForecast> weatherForecastList, Context context) {
        this.weatherForecastList = weatherForecastList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewDay.setText(weatherForecastList.get(position).getDay());
        holder.textViewDescription.setText(weatherForecastList.get(position).getCountryName());
        holder.textViewTemperature.setText(weatherForecastList.get(position).getMax_temperature());

        String iconCode = weatherForecastList.get(position).getWeatherIconLink();
        Picasso.get().load("https://openweathermap.org/img/wn/" + iconCode + "@2x.png")
                .into(holder.imageViewWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return weatherForecastList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewDay;
        private TextView textViewDescription;
        private TextView textViewTemperature;
        private ImageView imageViewWeatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewDay = itemView.findViewById(R.id.textViewDay);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
            imageViewWeatherIcon = itemView.findViewById(R.id.imageView);
        }
    }

}