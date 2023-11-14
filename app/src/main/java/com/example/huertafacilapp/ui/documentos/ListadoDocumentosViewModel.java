package com.example.huertafacilapp.ui.documentos;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.huertafacilapp.models.Documento;
import com.example.huertafacilapp.request.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListadoDocumentosViewModel extends AndroidViewModel {
  private Context context;
  private ArrayList<Documento> Documentos;
  private MutableLiveData<ArrayList<Documento>> listaDocumentos;


  public ListadoDocumentosViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    listaDocumentos = new MutableLiveData<>();
    buscarDocumentos();
  }

  public LiveData<ArrayList<Documento>> getDocumentos(){
    return listaDocumentos;
  }

  private void buscarDocumentos(){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty())return;

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<List<Documento>> call = end.documentos(token);
    call.enqueue(new Callback<List<Documento>>() {
      @Override
      public void onResponse(Call<List<Documento>> call, Response<List<Documento>> response) {
        if(response.isSuccessful()){
          if(response.body()!=null){
            Documentos = (ArrayList<Documento>) response.body();
            listaDocumentos.setValue(Documentos);
          }
        }
      }

      @Override
      public void onFailure(Call<List<Documento>> call, Throwable t) {
        Log.d("Error","Ocurrio un problema al obtener los documentos");
      }
    });
  }
}