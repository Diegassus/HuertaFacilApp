package com.example.huertafacilapp.request;

import com.example.huertafacilapp.models.CurrentWeather;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class WeatherHttpClient {
    private static final String BASE_URL = "https://api.openweathermap.org";
    private static Retrofit retrofit = null;
    public static Retrofit getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface OpenWeatherMapService {

        String CURRENT = "/data/2.5/weather";

        @GET(CURRENT)
        Call<CurrentWeather> getCurrentWeatherByGeoCoordinates(@QueryMap Map<String, String> options);

    }
}

/*
* helper.getCurrentWeatherByGeoCoordinates(5.6037, 0.1870, new CurrentWeatherCallback() {
     @Override
     public void onSuccess(CurrentWeather currentWeather) {
         Log.v(TAG, "Coordinates: " + currentWeather.getCoord().getLat() + ", "+currentWeather.getCoord().getLon() +"\n"
                         +"Weather Description: " + currentWeather.getWeather().get(0).getDescription() + "\n"
                         +"Temperature: " + currentWeather.getMain().getTempMax()+"\n"
                         +"Wind Speed: " + currentWeather.getWind().getSpeed() + "\n"
                         +"City, Country: " + currentWeather.getName() + ", " + currentWeather.getSys().getCountry()
         );
     }

     @Override
     public void onFailure(Throwable throwable) {
         Log.v(TAG, throwable.getMessage());
     }
 });
*
* */