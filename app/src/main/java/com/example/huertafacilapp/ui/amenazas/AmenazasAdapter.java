package com.example.huertafacilapp.ui.amenazas;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Amenazas;
import com.example.huertafacilapp.models.Biopreparados;
import com.example.huertafacilapp.ui.biopreparados.BiopreparadosAdapter;

import java.util.ArrayList;

public class AmenazasAdapter extends RecyclerView.Adapter<AmenazasAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Amenazas> biopreparados;
  private LayoutInflater li;

  public AmenazasAdapter(Context context, ArrayList<Amenazas> biopreparados, LayoutInflater li) {
    this.context = context;
    this.biopreparados = biopreparados;
    this.li = li;
  }

  @NonNull
  @Override
  public AmenazasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_amenaza, parent, false);
    return new AmenazasAdapter.ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull AmenazasAdapter.ViewHolder holder, int position) {
    Glide.with(context).load(biopreparados.get(position).getFoto()).into(holder.foto);
    holder.nombre.setText(biopreparados.get(position).getNombre());
    holder.descripcion.setText(biopreparados.get(position).getDescripcion());
    holder.boton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putInt("AmenazaId", biopreparados.get(position).getId());
        Navigation.findNavController(view).navigate(R.id.nav_curasFragment,bundle);
      }
    });
  }

  @Override
  public int getItemCount() {
    return biopreparados.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView nombre;
    Button boton;
    TextView descripcion;
    ImageView foto;

    public ViewHolder(@NonNull View v) {
      super(v);
      nombre = v.findViewById(R.id.tvNombreBio);
      foto = v.findViewById(R.id.ivFotoBio);
      descripcion = v.findViewById(R.id.tvDescBio);
      boton = v.findViewById(R.id.btnCuras);
    }
  }
}
