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
import android.content.SharedPreferences;
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
import com.example.huertafacilapp.models.RecordatorioVista;
import com.example.huertafacilapp.request.ApiClient;
import com.example.huertafacilapp.ui.HomeActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {
    private Context context;
    private ArrayList<RecordatorioVista> recordatorios;
    private MutableLiveData<ArrayList<RecordatorioVista>> mutableRecordatorios;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        mutableRecordatorios = new MutableLiveData<>();

    }

    public LiveData<ArrayList<RecordatorioVista>> getRecordatorios(){
        return mutableRecordatorios;
    }

    public void obtenerRecuperatorios(){
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        String token = sp.getString("token","");
        if(token.isEmpty()) return ;

        ApiClient.IEndpoint end = ApiClient.getApi();
        Call<List<RecordatorioVista>> call = end.obtenerRecordatorios(token);
        call.enqueue(new Callback<List<RecordatorioVista>>() {
            @Override
            public void onResponse(Call<List<RecordatorioVista>> call, Response<List<RecordatorioVista>> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        Log.d("Recordatorios",response.body().toString());
                        recordatorios = (ArrayList<RecordatorioVista>) response.body();
                        mutableRecordatorios.setValue(recordatorios);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<RecordatorioVista>> call, Throwable t) {
                Log.d("Recordatorios ERROR",t.getMessage());
            }
        });
    }
}

