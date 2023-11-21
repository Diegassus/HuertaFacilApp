package com.example.huertafacilapp.ui.amenazas;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.huertafacilapp.models.Amenazas;
import com.example.huertafacilapp.models.Planta;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmenazasViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<Amenazas> amenazas;
  private MutableLiveData<ArrayList<Amenazas>> mutable;


  public AmenazasViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    mutable = new MutableLiveData<>();
  }

  public LiveData<ArrayList<Amenazas>> getAmenazas(){
    return mutable;
  }

  public void recuperarDatos(Bundle bundle){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<List<Amenazas>> call = end.obtenerAmenazas(token,bundle.getInt("PlantaId"));
    call.enqueue(new Callback<List<Amenazas>>() {
      @Override
      public void onResponse(Call<List<Amenazas>> call, Response<List<Amenazas>> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            amenazas = (ArrayList<Amenazas>) response.body();
            mutable.setValue(amenazas);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Amenazas>> call, Throwable t) {
        Log.d("Error","Error al obtener la planta");
      }
    });
  }
}