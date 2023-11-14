package com.example.huertafacilapp.ui.documentos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Documento;

import java.util.ArrayList;

public class DocumentoAdapter extends RecyclerView.Adapter<DocumentoAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Documento> documentos;
  private LayoutInflater li;

  public DocumentoAdapter(Context context, ArrayList<Documento> documentos, LayoutInflater li){
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

      descargar.setOnClickListener(v ->{
        // llamar al endpoint para descargar el documento
      });

      ver.setOnClickListener(v ->{
        // ir al detalle del documento
      });
    }
  }
}
