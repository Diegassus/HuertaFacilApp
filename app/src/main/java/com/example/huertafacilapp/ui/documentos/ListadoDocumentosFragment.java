package com.example.huertafacilapp.ui.documentos;

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
import com.example.huertafacilapp.databinding.FragmentListadoDocumentosBinding;
import com.example.huertafacilapp.models.Documento;
import com.example.huertafacilapp.ui.listadoPrincipal.ListadoPrincipalAdapter;

import java.util.ArrayList;

public class ListadoDocumentosFragment extends Fragment {

  private ListadoDocumentosViewModel vm;
  private FragmentListadoDocumentosBinding binding;

  public static ListadoDocumentosFragment newInstance() {
    return new ListadoDocumentosFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    vm = new ViewModelProvider(this).get(ListadoDocumentosViewModel.class);
    binding = FragmentListadoDocumentosBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listaDocumentos;
    GridLayoutManager gm = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL,false);

    rv.setLayoutManager(gm);

    vm.getDocumentos().observe(getViewLifecycleOwner(), new Observer<ArrayList<Documento>>() {
      @Override
      public void onChanged(ArrayList<Documento> documentos) {
        rv.setAdapter(new DocumentoAdapter(getContext(), documentos,getLayoutInflater()));
      }
    });

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    vm = new ViewModelProvider(this).get(ListadoDocumentosViewModel.class);
    // TODO: Use the ViewModel
  }

}