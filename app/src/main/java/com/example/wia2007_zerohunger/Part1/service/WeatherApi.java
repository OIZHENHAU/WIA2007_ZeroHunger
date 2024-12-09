package com.example.wia2007_zerohunger.Part1.service;

import com.example.wia2007_zerohunger.Part1.model.WeatherModel;
import com.example.wia2007_zerohunger.Part1.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET(Constants.SUB_URL)
    Call<WeatherModel> getWeatherByLocation(@Query("lat") double userLatitude, @Query("lon") double userLongitude);

    @GET(Constants.SUB_URL)
    Call<WeatherModel> getWeatherByCityName(@Query("q") String cityName);
}

