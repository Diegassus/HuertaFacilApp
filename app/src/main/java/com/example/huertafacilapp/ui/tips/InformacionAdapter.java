package com.example.huertafacilapp.ui.tips;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.models.Tips;

import java.util.ArrayList;

public class InformacionAdapter extends RecyclerView.Adapter<InformacionAdapter.ViewHolder> {
  private Context context;
  private ArrayList<Tips> tips;
  private LayoutInflater li;

  public InformacionAdapter(Context context, ArrayList<Tips> tips, LayoutInflater li) {
    this.context = context;
    this.tips = tips;
    this.li = li;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View root = li.inflate(R.layout.item_info, parent, false);
    return new ViewHolder(root);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    holder.texto.setText(tips.get(position).getDescripcion());
  }

  @Override
  public int getItemCount() {
    return tips.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder{
    TextView texto;
    public ViewHolder(@NonNull View itemView){
      super(itemView);
      texto = itemView.findViewById(R.id.tvInfo);
    }
  }
}
