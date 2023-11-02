package com.example.huertafacilapp.ui.listadoPrincipal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.PlantaListado;

import java.util.ArrayList;

public class ListadoPrincipalAdapter extends RecyclerView.Adapter<ListadoPrincipalAdapter.ViewHolder> {
  private Context context;
  private ArrayList<PlantaListado> plantas;
  private LayoutInflater li;

  public ListadoPrincipalAdapter(Context context, ArrayList<PlantaListado> plantas, LayoutInflater li){
    this.context = context;
    this.plantas = plantas;
    this.li = li;
  }

  @NonNull
  @Override
  public ListadoPrincipalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_listado_principal, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ListadoPrincipalAdapter.ViewHolder holder, int position) {
    Glide.with(context).load(plantas.get(position).getFoto()).into(holder.foto);
    holder.nombre.setText(plantas.get(position).getNombre());
  }

  @Override
  public int getItemCount() {
    return plantas.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView nombre;
    ImageView foto;
    CardView card;
    public ViewHolder(@NonNull View itemView){
      super(itemView);
      nombre = itemView.findViewById(R.id.tvNombre);
      foto = itemView.findViewById(R.id.ivFoto);
      card = itemView.findViewById(R.id.card);

      card.setOnClickListener(v ->{
        //TODO: ir a la vista del detalle de la planta
      });
    }
  }
}
