package com.example.huertafacilapp.ui.rotaciones;

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

import com.example.huertafacilapp.models.PlantaListado;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RotacionesViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<PlantaListado> plantas;
  private MutableLiveData<ArrayList<PlantaListado>> listaPlantas;

  public RotacionesViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    listaPlantas = new MutableLiveData<>();
  }

  public LiveData<ArrayList<PlantaListado>> getPlantas() {
    return listaPlantas;
  }

  public void buscarPlantas(Bundle bundle){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty()){
      return;
    }

    ApiClient.IEndpoint endpoint = ApiClient.getApi();
    Call<List<PlantaListado>> call = endpoint.rotaciones(token,bundle.getInt("PlantaId"));
    call.enqueue(new Callback<List<PlantaListado>>() {
      @Override
      public void onResponse(Call<List<PlantaListado>> call, Response<List<PlantaListado>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            plantas = (ArrayList<PlantaListado>) response.body();
            listaPlantas.setValue(plantas);
          }
        }
      }

      @Override
      public void onFailure(Call<List<PlantaListado>> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }
}