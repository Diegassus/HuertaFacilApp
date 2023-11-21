package com.example.huertafacilapp.ui.curas;

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
import com.example.huertafacilapp.databinding.FragmentCurasBinding;
import com.example.huertafacilapp.ui.biopreparados.BiopreparadosAdapter;

public class CurasFragment extends Fragment {

  private CurasViewModel mViewModel;
  private FragmentCurasBinding binding;

  public static CurasFragment newInstance() {
    return new CurasFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(CurasViewModel.class);
    binding = FragmentCurasBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listadoCuras;
    GridLayoutManager gm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
    rv.setLayoutManager(gm);

    mViewModel.getBiopreparados().observe(getViewLifecycleOwner(), biopreparados -> {
      rv.setAdapter(new BiopreparadosAdapter(getContext(), biopreparados,getLayoutInflater()));
    });

    mViewModel.recuperarDatos(getArguments());

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(CurasViewModel.class);
    // TODO: Use the ViewModel
  }

}