package com.example.huertafacilapp.ui.biopreparados;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.huertafacilapp.models.Biopreparados;
import com.example.huertafacilapp.models.PlantaListado;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BiopreparadosViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<Biopreparados> biopreparados;
  private MutableLiveData<ArrayList<Biopreparados>> mutableBiopreparados;

  public BiopreparadosViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    mutableBiopreparados = new MutableLiveData<>();
  }

  public LiveData<ArrayList<Biopreparados>> getBiopreparados(){
    return mutableBiopreparados;
  }

  public void recuperarDatos(Bundle bundle){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty()){
      return;
    }

    ApiClient.IEndpoint endpoint = ApiClient.getApi();
    Call<List<Biopreparados>> call = endpoint.obtenerBonificadores(token,bundle.getInt("PlantaId"));
    call.enqueue(new Callback<List<Biopreparados>>() {
      @Override
      public void onResponse(Call<List<Biopreparados>> call, Response<List<Biopreparados>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            biopreparados = (ArrayList<Biopreparados>) response.body();
            mutableBiopreparados.setValue(biopreparados);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Biopreparados>> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }
}