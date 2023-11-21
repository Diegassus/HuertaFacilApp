package com.example.huertafacilapp.ui.biopreparados;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Biopreparados;

import java.util.ArrayList;

public class BiopreparadosAdapter extends RecyclerView.Adapter<BiopreparadosAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Biopreparados> biopreparados;
  private LayoutInflater li;

  public BiopreparadosAdapter(Context context, ArrayList<Biopreparados> biopreparados, LayoutInflater li) {
    this.context = context;
    this.biopreparados = biopreparados;
    this.li = li;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_biopreparado, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Glide.with(context).load(biopreparados.get(position).getFoto()).into(holder.foto);
    holder.nombre.setText(biopreparados.get(position).getNombre());
    holder.ingredientes.setText(biopreparados.get(position).getIngredientes());
    holder.descripcion.setText(biopreparados.get(position).getDescripcion());
  }

  @Override
  public int getItemCount() {
    return biopreparados.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView nombre;
    TextView ingredientes;
    TextView descripcion;
    ImageView foto;

    public ViewHolder(@NonNull View v) {
      super(v);
      nombre = v.findViewById(R.id.tvNombreBio);
      ingredientes = v.findViewById(R.id.tvPreparacion);
      descripcion = v.findViewById(R.id.tvDescBio);
      foto = v.findViewById(R.id.ivFotoBio);
    }
  }
}
