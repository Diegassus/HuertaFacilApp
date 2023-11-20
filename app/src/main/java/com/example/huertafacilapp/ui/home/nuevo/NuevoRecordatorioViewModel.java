package com.example.huertafacilapp.ui.home.nuevo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.huertafacilapp.models.EnvioRegistro;
import com.example.huertafacilapp.models.PlantaVista;
import com.example.huertafacilapp.models.Tipo_RecordatorioVista;
import com.example.huertafacilapp.request.ApiClient;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevoRecordatorioViewModel extends AndroidViewModel {
  public Context context;
  private List<PlantaVista> datosPlanta;
  private List<Tipo_RecordatorioVista> datosRecordatorio;
  private MutableLiveData<List<PlantaVista>> plantas;
  private MutableLiveData<List<Tipo_RecordatorioVista>> recordatorios;


  public NuevoRecordatorioViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    plantas = new MutableLiveData<>();
    recordatorios = new MutableLiveData<>();
  }

  public LiveData<List<PlantaVista>> getPlantas(){
    return plantas;
  }

  public LiveData<List<Tipo_RecordatorioVista>> getRecordatorios(){
    return recordatorios;
  }

  public void recuperarDatos(){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<List<PlantaVista>> call = end.nombresPlantas(token);
    call.enqueue(new Callback<List<PlantaVista>>() {
      @Override
      public void onResponse(Call<List<PlantaVista>> call, Response<List<PlantaVista>> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            plantas.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<List<PlantaVista>> call, Throwable t) {
        Log.d("Error","Error al obtener la planta");
      }
    });

    Call<List<Tipo_RecordatorioVista>> call2 = end.tiposRecordatorios(token);
    call2.enqueue(new Callback<List<Tipo_RecordatorioVista>>() {
      @Override
      public void onResponse(Call<List<Tipo_RecordatorioVista>> call, Response<List<Tipo_RecordatorioVista>> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            recordatorios.setValue(response.body());
          }
        }
      }

      @Override
      public void onFailure(Call<List<Tipo_RecordatorioVista>> call, Throwable t) {
        Log.d("Error","Error al obtener la planta");
      }
    });
  }

  public void crearRecordatorio(int plantaVista,int tipo_RecordatorioVista, String evento){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    EnvioRegistro envio = new EnvioRegistro(plantaVista,tipo_RecordatorioVista,evento);
    Call<String> call = end.crearRecordatorio(token,envio);
    call.enqueue(new Callback<String>() {
      @Override
      public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            Toast.makeText(context, "Registro creado", Toast.LENGTH_SHORT).show();
          }
        }
      }

      @Override
      public void onFailure(Call<String> call, Throwable t) {
        Log.d("Error","Error al cargar el registro");
      }
    });
  }

}