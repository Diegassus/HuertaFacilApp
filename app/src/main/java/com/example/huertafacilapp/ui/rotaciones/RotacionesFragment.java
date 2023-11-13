package com.example.huertafacilapp.ui.rotaciones;

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
import com.example.huertafacilapp.databinding.FragmentRotacionesBinding;
import com.example.huertafacilapp.ui.listadoPrincipal.ListadoPrincipalAdapter;

public class RotacionesFragment extends Fragment {

  private RotacionesViewModel mViewModel;
  private FragmentRotacionesBinding binding;

  public static RotacionesFragment newInstance() {
    return new RotacionesFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentRotacionesBinding.inflate(inflater, container, false);
    View root = binding.getRoot();
    mViewModel = new ViewModelProvider(this).get(RotacionesViewModel.class);
    RecyclerView rv = binding.listadoRotaciones;
    GridLayoutManager gm = new GridLayoutManager(
        getContext(),
        1,
        GridLayoutManager.VERTICAL,
        false
    );
    rv.setLayoutManager(gm);

    mViewModel.buscarPlantas(getArguments());

    mViewModel.getPlantas().observe(getViewLifecycleOwner(), plantas -> {
      rv.setAdapter(new ListadoPrincipalAdapter(getContext(), plantas, getLayoutInflater()));
    });

    return root;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(RotacionesViewModel.class);
  }

}