package com.example.huertafacilapp.ui.contrarias;

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
import android.widget.AbsListView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentContrariasBinding;
import com.example.huertafacilapp.ui.listadoPrincipal.ListadoPrincipalAdapter;

public class ContrariasFragment extends Fragment {

  private ContrariasViewModel mViewModel;
  private FragmentContrariasBinding binding;

  public static ContrariasFragment newInstance() {
    return new ContrariasFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(ContrariasViewModel.class);
    binding = FragmentContrariasBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listadoContrarias;
    GridLayoutManager gm = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
    rv.setLayoutManager(gm);

    mViewModel.getPlantas().observe(getViewLifecycleOwner(), plantas -> {
      rv.setAdapter(new ListadoPrincipalAdapter(getContext(), plantas,getLayoutInflater()));
    });

    mViewModel.buscarPlantas(getArguments());

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(ContrariasViewModel.class);
  }

}