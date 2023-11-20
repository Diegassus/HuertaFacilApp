package com.example.huertafacilapp.ui.home.detalle;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.huertafacilapp.models.EnvioRegistroUpdate;
import com.example.huertafacilapp.models.PlantaVista;
import com.example.huertafacilapp.models.RecordatorioVista;
import com.example.huertafacilapp.models.Tipo_RecordatorioVista;
import com.example.huertafacilapp.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleRecordatorioViewModel extends AndroidViewModel {
  private Context context;
  private int recordatorioId;
  private RecordatorioVista recordatorio;
  private MutableLiveData<RecordatorioVista> recordatorioMutable;

  private MutableLiveData<List<PlantaVista>> plantas;
  private MutableLiveData<List<Tipo_RecordatorioVista>> recordatorios;

  public DetalleRecordatorioViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    recordatorioMutable = new MutableLiveData<>();
    plantas = new MutableLiveData<>();
    recordatorios = new MutableLiveData<>();
  }

  public LiveData<List<PlantaVista>> getPlantas(){
    return plantas;
  }

  public LiveData<List<Tipo_RecordatorioVista>> getRecordatorios(){
    return recordatorios;
  }

  public LiveData<RecordatorioVista> getRecordatorio(){
    return recordatorioMutable;
  }

  public void recuperarRecordatorio(Bundle bundle){
    recordatorioId = bundle.getInt("recordatorio");
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<RecordatorioVista> call = end.verRecordatorio(token,recordatorioId);
    call.enqueue(new Callback<RecordatorioVista>() {


      @Override
      public void onResponse(Call<RecordatorioVista> call, Response<RecordatorioVista> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            recordatorio = response.body();
            recordatorioMutable.setValue(recordatorio);
            recuperarDatos();
          }
        }
      }

      @Override
      public void onFailure(Call<RecordatorioVista> call, Throwable t) {
        Log.d("Error","Error al obtener la planta");
      }
    });
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

  public int encontrarPosicionPorIdTipoRecordatorio(List<Tipo_RecordatorioVista> lista) {
    for (int i = 0; i < lista.size(); i++) {
      Tipo_RecordatorioVista tipoRecordatorio = lista.get(i);
      if (tipoRecordatorio.getTitulo().equals( recordatorio.getRecordatorio())) {
        return i;
      }
    }
    return 0; // Si no se encuentra, devuelve la primera posición como valor predeterminado
  }

  public int encontrarPosicionPorIdPlanta(List<PlantaVista> lista) {
    for (int i = 0; i < lista.size(); i++) {
      PlantaVista plantas = lista.get(i);
      if (plantas.getPlanta().equals(recordatorio.getPlanta())) {
        return i;
      }
    }
    return 0; // Si no se encuentra, devuelve la primera posición como valor predeterminado
  }

  public void crearRecordatorio(int plantaVista,int tipo_RecordatorioVista, String evento){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    EnvioRegistroUpdate envio = new EnvioRegistroUpdate(recordatorio.getId(),plantaVista,tipo_RecordatorioVista,evento);
    Call<String> call = end.actualizarRecordatorio(token,envio);
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

  public void eliminarRecordatorio(){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");

    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<String> call = end.eliminarRecordatorio(token,recordatorio.getId());
    call.enqueue(new Callback<String>() {

      @Override
      public void onResponse(Call<String> call, Response<String> response) {
        if(response.isSuccessful()){
          if(response.body() != null){
            Toast.makeText(context, "Registro eliminado", Toast.LENGTH_SHORT).show();
          }
        }
      }

      @Override
      public void onFailure(Call<String> call, Throwable t) {
        Log.d("Error","Error al eliminar el registro");
      }
    });
  }

}