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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.huertafacilapp.models.CurrentWeather;
import com.example.huertafacilapp.models.OpenWeatherHelper;
import com.example.huertafacilapp.ui.HomeActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {
    private Context context;
    private OpenWeatherHelper helper;
    private FusedLocationProviderClient flpc;
    private ArrayList<String> consejos = new ArrayList<>();
    private MutableLiveData<Boolean> climaCargado;
    private MutableLiveData<String> consejo;

    public HomeViewModel() {
        helper = new OpenWeatherHelper("");
        consejos.add("Recuerda regar las plantas en la mañana o la tarde. Nunca cundo el sol esta en su punto mas fuerte");
        consejos.add("No olvides revisar todos los dias que tus plantas no se hayan enfermado");
        consejos.add("No dejes de mover el compost!!!");
    }

    public LiveData<Boolean> getClimaCargado (){
        if (climaCargado == null) {
            this.climaCargado = new MutableLiveData<>();
            climaCargado.setValue(false);
        }
        return climaCargado;
    }

    public LiveData<String> getConsejo(){
        if(consejo == null) {
            this.consejo = new MutableLiveData<>();
            consejo.setValue(this.consejos.get((int) (Math.random() * (3))));
        }
        return consejo;
    }

    public void cargarClima(boolean value) {
        climaCargado.setValue(value);
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

