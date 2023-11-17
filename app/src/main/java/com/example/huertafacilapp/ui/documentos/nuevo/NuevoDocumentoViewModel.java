package com.example.huertafacilapp.ui.documentos.nuevo;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.huertafacilapp.models.ObjetoDocument;
import com.example.huertafacilapp.request.ApiClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class NuevoDocumentoViewModel extends AndroidViewModel {
  private Context context;
  private String directorio;
  private MutableLiveData<String> error;
  private MutableLiveData<Bitmap> fotoBitmap;
  private ObjetoDocument objeto;
  private byte[] foto;

  public NuevoDocumentoViewModel(@NonNull Application application) {
    super(application);
    context = application.getApplicationContext();
    error = new MutableLiveData<>();
    fotoBitmap = new MutableLiveData<>();
  }

  public LiveData<Bitmap> getFotoBitmap(){
    return fotoBitmap;
  }

  public LiveData<String> getError(){
    return error;
  }
  public void guardarDirectorio(Bundle bundle){
    directorio = bundle.getString("directorio");
  }

  public void validarInputs(String titulo, String cuerpo){
    if(titulo.trim().length()<4 || cuerpo.trim().length()<4 ){
      error.setValue("Los campos no pueden estar vacios o ser menores a 4 caracteres");
      return;
    }
    File archivo = new File(directorio, titulo+".dat");
    try{
      FileOutputStream fos = new FileOutputStream(archivo);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      ObjectOutputStream oos = new ObjectOutputStream(bos);

      if(this.foto==null){
        this.objeto = new ObjetoDocument(titulo, cuerpo);
      }else{
        this.objeto = new ObjetoDocument(this.foto, titulo, cuerpo);
      }

      // Con esto se guarda el archivo, lo comento para realizar las practicas respectivas en la app
      oos.writeObject(objeto);
      bos.flush();
      oos.close();

      // se registra en la base de datos y el servidor el documento
      subirDocumento(archivo);

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private void subirDocumento(File archivo){
    SharedPreferences sp = context.getSharedPreferences("token.xml",0);
    String token = sp.getString("token","");
    if(token.isEmpty()) return;

    RequestBody requestFile = RequestBody.create(
        MediaType.parse("multipart/form-data"),archivo
    );

    MultipartBody.Part file =
        MultipartBody.Part.createFormData("file",archivo.getName(),requestFile);

    ApiClient.IEndpoint end = ApiClient.getApi();
    Call<Boolean> call = end.guardarDocumento(token,file);
    call.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if(response.isSuccessful()){
          if(response.body()){
            Toast.makeText(context, "Documento guardado", Toast.LENGTH_SHORT).show();
          }
        }
      }

      @Override
      public void onFailure(Call<Boolean> call, Throwable t) {
        Log.e("Error",t.getMessage());
      }
    });
  }

  public void respuestaDeCamara(int requestCode, int resultCode, Intent data, int REQUEST_IMAGE_CAPTURE){
    if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
      Bundle extras = data.getExtras();
      Bitmap foto = (Bitmap) extras.get("data");

      // optimizar la foto
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      foto.compress(Bitmap.CompressFormat.PNG, 100, baos);
      fotoBitmap.setValue(foto);

      this.foto = baos.toByteArray();
    }
  }
}