package com.example.huertafacilapp.ui.listadoPrincipal.detalle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Planta;
import com.example.huertafacilapp.request.ApiClient;
import com.example.huertafacilapp.ui.rotaciones.RotacionesFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlantaDetalleViewModel extends AndroidViewModel {
  public Context context;
  public int plantaId;
  public MutableLiveData<Planta> MutablePlanta;
  public MutableLiveData<Boolean> MutableFavorito;

  public PlantaDetalleViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    MutablePlanta = new MutableLiveData<>();
    MutableFavorito = new MutableLiveData<>();
  }

  public LiveData<Boolean> getFavorito(){
    return MutableFavorito;
  }
  public LiveData<Planta> getPlanta(){
    return MutablePlanta;
  }

  // hacer llamado al endpoint (Una vez finalizados los cambios del mismo)
  public void recuperarPlanta(Bundle bundle){
    plantaId = bundle.getInt("plantaId");
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<Planta> call = end.plantaDetalle(token,plantaId);
    call.enqueue(new Callback<Planta>() {
      @Override
      public void onResponse(Call<Planta> call, Response<Planta> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            MutablePlanta.setValue(response.body());
            Log.d("Planta",response.body().getLogo());
            esFavorito();
          }
        }
      }

      @Override
      public void onFailure(Call<Planta> call, Throwable t) {
        Log.d("Error","Error al obtener la planta");
      }
    });
  }

  public void esFavorito(){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<Boolean> call = end.esFavorito(token,plantaId);
    call.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            MutableFavorito.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {
        Log.d("Error","Error al obtener si la planta esta o no en fav");
      }
    });
  }
  public void changeFavorito (){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty())return;
    ApiClient.IEndpoint end = ApiClient.getApi();


    Call<Boolean> call = end.guardarFavorito(token,plantaId);
    call.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            MutableFavorito.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {
        Log.d("Error","Error al obtener si la planta esta o no en favorito");
      }
    });
  }

  public void verRotaciones(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_rotaciones,bundle);
  }

  public void verTips(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_tipsFragment,bundle);
  }

  public void verUsos(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_usosFragment,bundle);
  }

  public void verBonos(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_biopreparadosFragment,bundle);
  }

  public void verEnfermedades(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_amenazasFragment,bundle);
  }

  public void verCompatibles(View v){
    Bundle bundle = new Bundle();
    bundle.putInt("PlantaId",plantaId);
    Navigation.findNavController(v).navigate(R.id.nav_contrariasFragment,bundle);
  }
}