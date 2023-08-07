package com.example.huertafacilapp.models;

import com.example.huertafacilapp.request.WeatherHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OpenWeatherHelper {
    private static final String APPID = "appId";
    private static final String UNITS = "units";
    private static final String LANGUAGE = "lang";
    private static final String QUERY = "q";
    private static final String CITY_ID = "id";
    private static final String LATITUDE = "lat";
    private static final String LONGITUDE = "lon";
    private static final String ZIP_CODE = "zip";

    private final WeatherHttpClient.OpenWeatherMapService openWeatherMapService = WeatherHttpClient.getClient().create(WeatherHttpClient.OpenWeatherMapService.class);

    private final Map<String, String> options = new HashMap<>();


    public OpenWeatherHelper(String apiKey){
        options.put(APPID, apiKey == null ? "" : apiKey);
    }

    //SETUP METHODS
    public void setUnits(String units){
        options.put(UNITS, units);
    }
    public void setLanguage(String lang) {
        options.put(LANGUAGE, lang);
    }


    private Throwable NoAppIdErrMessage() {
        return new Throwable("UnAuthorized. Please set a valid OpenWeatherMap API KEY.");
    }


    private Throwable NotFoundErrMsg(String str) {
        Throwable throwable = null;
        try {
            JSONObject obj = new JSONObject(str);
            throwable = new Throwable(obj.getString("message"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (throwable == null){
            throwable = new Throwable("An unexpected error occurred.");
        }


        return throwable;
    }

    public void getCurrentWeatherByGeoCoordinates(double latitude, double longitude, final CurrentWeatherCallback callback) {
        options.put(LATITUDE, String.valueOf(latitude));
        options.put(LONGITUDE, String.valueOf(longitude));
        openWeatherMapService.getCurrentWeatherByGeoCoordinates(options).enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {

            }
        });
    }
        public interface CurrentWeatherCallback{
            void onSuccess(CurrentWeather currentWeather);
            void onFailure(Throwable throwable);
        }

}
