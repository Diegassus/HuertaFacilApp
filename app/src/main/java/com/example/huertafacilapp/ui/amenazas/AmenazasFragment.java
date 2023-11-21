package com.example.huertafacilapp.ui.amenazas;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentAmenazasBinding;
import com.example.huertafacilapp.ui.biopreparados.BiopreparadosAdapter;

public class AmenazasFragment extends Fragment {

  private AmenazasViewModel mViewModel;
  private FragmentAmenazasBinding binding;

  public static AmenazasFragment newInstance() {
    return new AmenazasFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(AmenazasViewModel.class);
    binding = FragmentAmenazasBinding.inflate(getLayoutInflater(),container,false);

    RecyclerView rv = binding.listaAmenazas;
    GridLayoutManager gm= new GridLayoutManager(getContext(),1,GridLayoutManager.VERTICAL,false);
    rv.setLayoutManager(gm);

    mViewModel.getAmenazas().observe(getViewLifecycleOwner(), amenazas -> {
      rv.setAdapter(new AmenazasAdapter(getContext(),amenazas,getLayoutInflater()));
    });

    mViewModel.recuperarDatos(getArguments());

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(AmenazasViewModel.class);
    // TODO: Use the ViewModel
  }

}