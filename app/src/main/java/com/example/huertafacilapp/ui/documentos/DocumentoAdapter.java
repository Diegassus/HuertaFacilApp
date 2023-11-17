package com.example.huertafacilapp.ui.documentos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.DocumentoVista;
import com.example.huertafacilapp.models.ObjetoDocument;
import com.example.huertafacilapp.request.ApiClient;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

public class DocumentoAdapter extends RecyclerView.Adapter<DocumentoAdapter.ViewHolder> {
  private Context context;
  private ArrayList<DocumentoVista> documentos;
  private LayoutInflater li;

  public DocumentoAdapter(Context context, ArrayList<DocumentoVista> documentos, LayoutInflater li){
    this.context = context;
    this.documentos = documentos;
    this.li = li;
  }

  @NonNull
  @Override
  public DocumentoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.documento_item, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.titulo.setText(documentos.get(position).getTitulo());

    File file = new File(context.getFilesDir().toString() + "/" + documentos.get(position).getTitulo() + ".dat");
    if(file.exists()){
      holder.ver.setVisibility(View.VISIBLE);
      holder.descargar.setVisibility(View.GONE);

      holder.ver.setOnClickListener(v ->{
        Bundle bundle = new Bundle();
        bundle.putString("documentoDir", file.toString()); // le paso la ubicacion del documento
        Navigation.findNavController(v).navigate(R.id.nav_detalleDocumento, bundle);
      });
    }else{
      holder.ver.setVisibility(View.GONE);
      holder.descargar.setVisibility(View.VISIBLE);

      holder.descargar.setOnClickListener(v ->{
        SharedPreferences sp = context.getSharedPreferences("token.xml",0);
        String token = sp.getString("token", "");
        if(token.isEmpty()) return;

        ApiClient.IEndpoint endpoint = ApiClient.getApi();
        Call<ResponseBody> call = endpoint.descargar(token, documentos.get(position).getId());
        call.enqueue(new Callback<ResponseBody>() {
          @Override
          public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
            if(response.isSuccessful()){
              ResponseBody responseBody = response.body();
              if (responseBody != null) {
                File archivo = new File(file.toString());
                try{
                  byte[] bytes = responseBody.bytes();

                  FileOutputStream fos = new FileOutputStream(archivo);
                  BufferedOutputStream bos = new BufferedOutputStream(fos);
                  ObjectOutputStream oos = new ObjectOutputStream(bos);

                  ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                  ObjectInputStream ois = new ObjectInputStream(bis);
                  ObjetoDocument obj = (ObjetoDocument) ois.readObject();
                  ois.close();
                  bis.close();

                  // Con esto se guarda el archivo, lo comento para realizar las practicas respectivas en la app
                  oos.writeObject(obj);
                  bos.flush();
                  oos.close();

                  Navigation.findNavController(v).navigate(R.id.nav_listado_documentos);

                } catch (FileNotFoundException e) {
                  throw new RuntimeException(e);
                } catch (IOException e) {
                  throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                  throw new RuntimeException(e);
                }

              }
            }
          }

          @Override
          public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.d("ERROR", t.getMessage());
          }
        });
      });
    }
  }

  @Override
  public int getItemCount() {
    return documentos.size();
  }


  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView titulo;
    TextView descargar;
    TextView ver;

    public ViewHolder(@NonNull View itemView){
      super(itemView);
      titulo = itemView.findViewById(R.id.tvDocTitle);
      descargar = itemView.findViewById(R.id.tvDocDescargar);
      ver = itemView.findViewById(R.id.tvDocVer);
    }
  }
}
