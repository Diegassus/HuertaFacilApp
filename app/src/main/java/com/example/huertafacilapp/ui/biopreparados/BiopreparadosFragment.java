package com.example.huertafacilapp.ui.biopreparados;

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
import com.example.huertafacilapp.databinding.FragmentBiopreparadosBinding;

public class BiopreparadosFragment extends Fragment {

  private BiopreparadosViewModel mViewModel;
  private FragmentBiopreparadosBinding binding;

  public static BiopreparadosFragment newInstance() {
    return new BiopreparadosFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(BiopreparadosViewModel.class);
    binding = FragmentBiopreparadosBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listadoBio;
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
    mViewModel = new ViewModelProvider(this).get(BiopreparadosViewModel.class);
    // TODO: Use the ViewModel
  }

}