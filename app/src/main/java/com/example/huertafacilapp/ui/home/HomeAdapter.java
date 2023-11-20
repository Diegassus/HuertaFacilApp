package com.example.huertafacilapp.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.RecordatorioVista;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
  private Context context;
  private ArrayList<RecordatorioVista> recordatorios;
  private LayoutInflater li;

  public HomeAdapter(Context context, ArrayList<RecordatorioVista> recordatorios, LayoutInflater li) {
    this.context = context;
    this.recordatorios = recordatorios;
    this.li = li;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_recordatorio, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    // eventos OnClicks
    holder.nombre.setText(recordatorios.get(position).getRecordatorio() + " " + recordatorios.get(position).getPlanta());
    holder.hora.setText(recordatorios.get(position).getEvento().split("T")[0]);
    holder.card.setOnClickListener(v -> {
      Bundle bundle = new Bundle();
      bundle.putInt("recordatorio", recordatorios.get(position).getId());
      Navigation.findNavController(v).navigate(R.id.nav_detalleRecordatorioFragment, bundle);
    });
  }

  @Override
  public int getItemCount() {
    return recordatorios.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView hora;
    TextView nombre;
    CardView card;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      hora = itemView.findViewById(R.id.tvFecha);
      nombre = itemView.findViewById(R.id.tvNombreReco);
      card = itemView.findViewById(R.id.cardRecordatorio);
    }
  }
}
