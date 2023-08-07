package com.example.huertafacilapp.ui.home;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.content.PermissionChecker.checkSelfPermission;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.huertafacilapp.models.CurrentWeather;
import com.example.huertafacilapp.models.OpenWeatherHelper;
import com.example.huertafacilapp.ui.HomeActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class HomeViewModel extends ViewModel {
    private Context context;
    private OpenWeatherHelper helper;
    private FusedLocationProviderClient flpc;

    public HomeViewModel() {
        helper = new OpenWeatherHelper("");
    }

    @SuppressLint("MissingPermission")
    public void obtenerClima() {
        flpc.getLastLocation().addOnSuccessListener((Activity) this.context, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    helper.getCurrentWeatherByGeoCoordinates(location.getLatitude(), location.getLongitude(), new OpenWeatherHelper.CurrentWeatherCallback() {
                        @Override
                        public void onSuccess(CurrentWeather currentWeather) {
                            Toast.makeText(context, "\"Coordinates: \" + currentWeather.getCoord().getLat() + \", \" + currentWeather.getCoord().getLon() + \"\\n\"\n" +
                                    "                                    + \"Weather Description: \" + currentWeather.getWeather().get(0).getDescription() + \"\\n\"\n" +
                                    "                                    + \"Temperature: \" + currentWeather.getMain().getTempMax() + \"\\n\"\n" +
                                    "                                    + \"Wind Speed: \" + currentWeather.getWind().getSpeed() + \"\\n\"\n" +
                                    "                                    + \"City, Country: \" + currentWeather.getName() + \", \" + currentWeather.getSys().getCountry()\n" +
                                    "                            ", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Throwable throwable) {
                            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    public void init(Context context) {
        this.context = context;
        flpc = LocationServices.getFusedLocationProviderClient(this.context);
    }
}

