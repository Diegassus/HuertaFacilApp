package com.example.huertafacilapp.ui.usos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Usos;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class UsosAdapter extends RecyclerView.Adapter<UsosAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Usos> usos;
  private LayoutInflater li;

  public UsosAdapter(Context context, ArrayList<Usos> usos, LayoutInflater li){
    this.context = context;
    this.usos = usos;
    this.li = li;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_info,parent,false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.texto.setText(usos.get(position).getDescripcion());
  }

  @Override
  public int getItemCount() {
    return usos.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView texto;

    public ViewHolder(@NonNull View itemView){
      super(itemView);
      texto = itemView.findViewById(R.id.tvInfo);
    }
  }
}
