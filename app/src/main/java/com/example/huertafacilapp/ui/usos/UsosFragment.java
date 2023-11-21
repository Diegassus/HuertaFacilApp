package com.example.huertafacilapp.ui.usos;

import androidx.lifecycle.Observer;
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
import com.example.huertafacilapp.databinding.FragmentUsosBinding;
import com.example.huertafacilapp.models.Usos;

import java.util.ArrayList;

public class UsosFragment extends Fragment {

  private UsosViewModel mViewModel;
  private FragmentUsosBinding binding;

  public static UsosFragment newInstance() {
    return new UsosFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(UsosViewModel.class);
    binding = FragmentUsosBinding.inflate(getLayoutInflater(),container,false);

    RecyclerView rv = binding.listadoUsos;
    GridLayoutManager gm = new GridLayoutManager(
        getContext(),
        1,
        GridLayoutManager.VERTICAL,
        false);

    rv.setLayoutManager(gm);

    mViewModel.getUsos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Usos>>() {
      @Override
      public void onChanged(ArrayList<Usos> usos) {
        rv.setAdapter(new UsosAdapter(getContext(),usos,getLayoutInflater()));
      }
    });

    mViewModel.recuperarDatos(getArguments());

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(UsosViewModel.class);
    // TODO: Use the ViewModel
  }

}