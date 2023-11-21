package com.example.huertafacilapp.ui.usos;

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

import com.example.huertafacilapp.models.Usos;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UsosViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<Usos> usos;
  private MutableLiveData<ArrayList<Usos>> MutableUsos;

  public UsosViewModel(@NonNull Application application) {
    super(application);
    this.context = application.getApplicationContext();
    MutableUsos=  new MutableLiveData<>();
  }

  public LiveData<ArrayList<Usos>> getUsos(){
    return MutableUsos;
  }

  public void recuperarDatos(Bundle bundle){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty()){
      return;
    }

    ApiClient.IEndpoint endpoint = ApiClient.getApi();
    Call<List<Usos>> call = endpoint.obtenerUsos(token,bundle.getInt("PlantaId"));
    call.enqueue(new Callback<List<Usos>>() {
      @Override
      public void onResponse(Call<List<Usos>> call, Response<List<Usos>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            usos = (ArrayList<Usos>) response.body();
            MutableUsos.setValue(usos);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Usos>> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }
}