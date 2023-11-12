package com.example.huertafacilapp.ui.listadoPrincipal.detalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentPlantaDetalleBinding;

public class PlantaDetalle extends Fragment {

  private PlantaDetalleViewModel mViewModel;
  private FragmentPlantaDetalleBinding binding;

  public static PlantaDetalle newInstance() {
    return new PlantaDetalle();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentPlantaDetalleBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    mViewModel = new ViewModelProvider(this).get(PlantaDetalleViewModel.class);

    // recuperar la planta
    mViewModel.recuperarPlanta(getArguments());

    // observer <--- crear esta parte, la request funciona bien
    mViewModel.getPlanta().observe(getViewLifecycleOwner(), planta -> {
      Glide.with(getContext()).load(planta.getPortada()).into(binding.portada);
      binding.tvNombre.setText(planta.getNombre());
      binding.tvTransplante.setText(planta.getTransplante() + " dias");
      binding.tvRiego.setText(planta.getRiego() + " dias");
      binding.tvCosecha.setText(planta.getCosecha() + " dias");
      binding.tvSemillado.setText(planta.getSemillado() + " dias");
      binding.tvPoda.setText(planta.getPoda() + " dias");
      binding.tvInfoLuzTipo.setText("Esta planta necesita luz "+planta.getIluminacion().getNombre() +" y se clasifica como: " + planta.getTipo().getNombre());
    });

    mViewModel.getFavorito().observe(getViewLifecycleOwner(), favorito -> {
      Log.d("favorito", favorito.toString());
      binding.cbFavorito.setChecked(favorito);
      binding.cbFavorito.setOnClickListener(v -> {
        mViewModel.changeFavorito();
      });
    });



    return root;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(PlantaDetalleViewModel.class);
    // TODO: Use the ViewModel
  }

}