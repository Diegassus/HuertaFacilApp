package com.example.huertafacilapp.ui.favoritas;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentFavoritasBinding;
import com.example.huertafacilapp.databinding.FragmentPlantaDetalleBinding;
import com.example.huertafacilapp.models.PlantaListado;
import com.example.huertafacilapp.ui.listadoPrincipal.ListadoPrincipalAdapter;
import com.example.huertafacilapp.ui.listadoPrincipal.detalle.PlantaDetalleViewModel;

import java.util.ArrayList;

public class FavoritasFragment extends Fragment {

  private FavoritasViewModel vm;
  private FragmentFavoritasBinding binding;

  public static FavoritasFragment newInstance() {
    return new FavoritasFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentFavoritasBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    vm = new ViewModelProvider(this).get(FavoritasViewModel.class);
    vm.actualizar();

    // recuperar la planta
    RecyclerView rv = binding.listadoFavs;
    GridLayoutManager gm = new GridLayoutManager(
        getContext(),
        1,
        GridLayoutManager.VERTICAL,
        false);
    rv.setLayoutManager(gm);

    vm.getPlantas().observe(getViewLifecycleOwner(), new Observer<ArrayList<PlantaListado>>() {
      @Override
      public void onChanged(ArrayList<PlantaListado> plantas) {
        rv.setAdapter(
            new ListadoPrincipalAdapter(getContext(),plantas,getLayoutInflater())
        );
      }
    });

    return root;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    vm = new ViewModelProvider(this).get(FavoritasViewModel.class);
    // TODO: Use the ViewModel
  }

}