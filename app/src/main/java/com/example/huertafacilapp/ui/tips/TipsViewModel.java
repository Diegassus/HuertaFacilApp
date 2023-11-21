package com.example.huertafacilapp.ui.tips;

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

import com.example.huertafacilapp.models.Tips;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TipsViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<Tips> tips;
  private MutableLiveData<ArrayList<Tips>> MutableTips;

  public TipsViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    MutableTips = new MutableLiveData<>();
  }

  public LiveData<ArrayList<Tips>> getTips(){
    return MutableTips;
  }

  public void recuperarDatos(Bundle bundle){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty()){
      return;
    }

    ApiClient.IEndpoint endpoint = ApiClient.getApi();
    Call<List<Tips>> call = endpoint.obtenerTips(token,bundle.getInt("PlantaId"));
    call.enqueue(new Callback<List<Tips>>() {
      @Override
      public void onResponse(Call<List<Tips>> call, Response<List<Tips>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            tips = (ArrayList<Tips>) response.body();
            MutableTips.setValue(tips);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Tips>> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }
}