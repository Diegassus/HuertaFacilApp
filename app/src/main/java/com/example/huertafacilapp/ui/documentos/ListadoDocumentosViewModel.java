package com.example.huertafacilapp.ui.documentos;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.example.huertafacilapp.models.DocumentoVista;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoDocumentosViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<DocumentoVista> Documentos;
  private MutableLiveData<ArrayList<DocumentoVista>> listaDocumentos;


  public ListadoDocumentosViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    listaDocumentos = new MutableLiveData<>();
  }

  public LiveData<ArrayList<DocumentoVista>> getDocumentos(){
    return listaDocumentos;
  }

  public void actualizar(){
    buscarDocumentos();
  }

  private void buscarDocumentos(){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<List<DocumentoVista>> call = end.documentos(token);
    call.enqueue(new Callback<List<DocumentoVista>>() {

      @Override
      public void onResponse(Call<List<DocumentoVista>> call, Response<List<DocumentoVista>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            Documentos = (ArrayList<DocumentoVista>) response.body();
            listaDocumentos.setValue(Documentos);
            Log.d("Documentos",response.body().toString());
          }
        }
      }

      @Override
      public void onFailure(Call<List<DocumentoVista>> call, Throwable t) {
        Log.d("Error",t.getMessage());
      }
    });
  }

}