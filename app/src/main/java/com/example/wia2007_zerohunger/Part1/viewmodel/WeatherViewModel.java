package com.example.wia2007_zerohunger.Part1.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wia2007_zerohunger.Part1.model.WeatherModel;
import com.example.wia2007_zerohunger.Part1.service.RetrofitInstance;
import com.example.wia2007_zerohunger.Part1.service.WeatherApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherViewModel extends ViewModel {

    MutableLiveData<WeatherModel> weatherResponseLiveData;
    MutableLiveData<Boolean> progressBarLiveData;
    WeatherApi weatherService;

    public WeatherViewModel() {
        weatherResponseLiveData = new MutableLiveData<>();
        progressBarLiveData = new MutableLiveData<>();
        weatherService = RetrofitInstance.getRetrofit().create(WeatherApi.class);

    }

    //getter
    public MutableLiveData<WeatherModel> getWeatherResponseLiveData() {
        return weatherResponseLiveData;
    }

    //getter
    public MutableLiveData<Boolean> getProgressBarLiveData() {
        return progressBarLiveData;
    }

    public void sendRequestByLocation(Context context, double lat, double lon) {
        progressBarLiveData.setValue(true);

        Call<WeatherModel> call = weatherService.getWeatherByLocation(lat, lon);

        call.enqueue(new Callback<WeatherModel>() {

            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    weatherResponseLiveData.setValue(response.body());

                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
                progressBarLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                Log.d("onFailureByLocation", throwable.getLocalizedMessage());
                progressBarLiveData.setValue(false);
            }
        });

    }

    public void sendRequestByCityName(Context context, String cityName) {
        progressBarLiveData.setValue(true);

        Call<WeatherModel> call = weatherService.getWeatherByCityName(cityName);

        call.enqueue(new Callback<WeatherModel>() {

            @Override
            public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                if (response.isSuccessful()) {
                    weatherResponseLiveData.setValue(response.body());

                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
                progressBarLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<WeatherModel> call, Throwable throwable) {
                Log.d("onFailureByCityName", throwable.getLocalizedMessage());
                progressBarLiveData.setValue(false);
            }
        });
    }
}
