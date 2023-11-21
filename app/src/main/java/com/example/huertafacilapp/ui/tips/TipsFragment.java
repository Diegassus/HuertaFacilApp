package com.example.huertafacilapp.ui.tips;

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
import com.example.huertafacilapp.databinding.FragmentTipsBinding;

public class TipsFragment extends Fragment {

  private TipsViewModel mViewModel;
  private FragmentTipsBinding binding;

  public static TipsFragment newInstance() {
    return new TipsFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(TipsViewModel.class);
    binding = FragmentTipsBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listaTips;
    GridLayoutManager gm = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
    rv.setLayoutManager(gm);


    mViewModel.getTips().observe(getViewLifecycleOwner(), tips -> {
      rv.setAdapter(new InformacionAdapter(getContext(), tips, getLayoutInflater()));
    });


    mViewModel.recuperarDatos(getArguments());
    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(TipsViewModel.class);
    // TODO: Use the ViewModel
  }

}