package com.example.wia2007_zerohunger.Part1.model;

public class WeatherForecast {

    String day;
    String countryName;
    String max_temperature;
    String weatherIconLink;

    public WeatherForecast(String day, String countryName, String max_temperature, String weatherIconLink) {
        this.day = day;
        this.countryName = countryName;
        this.max_temperature = max_temperature;
        this.weatherIconLink = weatherIconLink;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getMax_temperature() {
        return max_temperature;
    }

    public void setMax_temperature(String max_temperature) {
        this.max_temperature = max_temperature;
    }

    public String getWeatherIconLink() {
        return weatherIconLink;
    }

    public void setWeatherIconLink(String weatherIconCode) {
        this.weatherIconLink = weatherIconCode;
    }
}