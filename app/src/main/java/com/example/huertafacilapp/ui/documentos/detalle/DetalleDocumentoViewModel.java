package com.example.huertafacilapp.ui.documentos.detalle;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.ObjetoDocument;
import com.example.huertafacilapp.request.ApiClient;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleDocumentoViewModel extends AndroidViewModel {
  private Context context;
  private String documentoDir;
  private ObjetoDocument documento;
  private MutableLiveData<ObjetoDocument> mutableObjeto;
  private MutableLiveData<Boolean> mutableEditable;
  private MutableLiveData<String> error;
  private MutableLiveData<Bitmap> fotoBitmap;
  private ObjetoDocument objeto;
  private byte[] foto;

  public DetalleDocumentoViewModel(@NonNull Application application) {
    super(application);
    this.context = application.getApplicationContext();
    this.mutableObjeto = new MutableLiveData<>();
    this.mutableEditable = new MutableLiveData<>();
    this.mutableEditable.setValue(false);
    this.error = new MutableLiveData<>();
    this.fotoBitmap = new MutableLiveData<>();
  }

  public void recuperarDirectorio(Bundle bundle){
    this.documentoDir = bundle.getString("documentoDir");
    recuperarDocumento();
  }

  public LiveData<Boolean> getEditable(){
    return this.mutableEditable;
  }
  public LiveData<Bitmap> getFotoBitmap(){
    return fotoBitmap;
  }

  public LiveData<String> getError(){
    return error;
  }
  public void changeEditable(){
    this.mutableEditable.setValue(Boolean.FALSE.equals(this.mutableEditable.getValue()));
  }
  public LiveData<ObjetoDocument> getDocumento(){
    return this.mutableObjeto;
  }

  private void recuperarDocumento(){
    File document = new File(this.documentoDir);
    try{
      FileInputStream fis = new FileInputStream(document);
      BufferedInputStream bis = new BufferedInputStream(fis);
      ObjectInputStream ois = new ObjectInputStream(bis);

      this.documento = (ObjetoDocument) ois.readObject();

      this.mutableObjeto.setValue(this.documento);
      this.foto= this.documento.getFoto();

    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  public void validarInputs(String titulo, String cuerpo){
    if(titulo.trim().length()<4 || cuerpo.trim().length()<4 ){
      error.setValue("Los campos no pueden estar vacios o ser menores a 4 caracteres");
      return;
    }
    File archivo = new File(documentoDir);
    try{
      FileOutputStream fos = new FileOutputStream(archivo);
      BufferedOutputStream bos = new BufferedOutputStream(fos);
      ObjectOutputStream oos = new ObjectOutputStream(bos);

      if(this.foto==null){
        this.objeto = new ObjetoDocument(titulo, cuerpo);
      }else{
        this.objeto = new ObjetoDocument(this.foto, titulo, cuerpo);
      }

      if(!this.documento.getTitulo().equals(this.objeto.getTitulo())){
        File nuevoArchivo = new File(documentoDir.replace(this.documento.getTitulo(), this.objeto.getTitulo()));
        this.documentoDir = nuevoArchivo.toString();
        archivo.renameTo(nuevoArchivo);
        archivo = nuevoArchivo;
      }

      // Con esto se guarda el archivo actualizado
      oos.writeObject(objeto);
      bos.flush();
      oos.close();

      // se cambia el archivo del servidor y se modifica en la base de datos.
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
    String titulo = this.documento.getTitulo();
    RequestBody tituloPart = RequestBody.create(MediaType.parse("text/plain"), titulo);
    Call<Boolean> call = end.actualizarDocumento(token,file,tituloPart);
    call.enqueue(new Callback<Boolean>() {
      @Override
      public void onResponse(Call<Boolean> call, Response<Boolean> response) {
        if(response.isSuccessful()){
          if(response.body()){
            Toast.makeText(context, "Documento guardado", Toast.LENGTH_LONG).show();
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

  public void eliminarDocumento(View v){
    File eliminar = new File(this.documentoDir);
    eliminar.delete();
    Navigation.findNavController(v).navigate(R.id.nav_listado_documentos);
  }
}