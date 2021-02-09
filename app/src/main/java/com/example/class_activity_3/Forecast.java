package com.example.class_activity_3;

public class Forecast {
    private String date_time;
    private String weather_description;
    private String feels_like_temp;


    public Forecast(String date_time, String weather_description, String feels_like_temp){
        this.weather_description = weather_description;
        this.feels_like_temp = feels_like_temp;
        this.date_time = date_time;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String name) {
        this.date_time = date_time;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String name) {
        this.weather_description = weather_description;
    }

    public String getFeels_like_temp() {
        return feels_like_temp;
    }

    public void setFeels_like_temp(String name) {
        this.feels_like_temp = feels_like_temp;
    }



}
