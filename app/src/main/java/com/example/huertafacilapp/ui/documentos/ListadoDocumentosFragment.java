package com.example.huertafacilapp.ui.documentos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentListadoDocumentosBinding;
import com.example.huertafacilapp.models.DocumentoVista;
import com.example.huertafacilapp.ui.listadoPrincipal.ListadoPrincipalAdapter;

import java.util.ArrayList;
import java.util.Objects;

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
    vm.actualizar();
    binding = FragmentListadoDocumentosBinding.inflate(inflater, container, false);

    RecyclerView rv = binding.listaDocumentos;
    GridLayoutManager gm = new GridLayoutManager(getContext(), 1,GridLayoutManager.VERTICAL,false);

    rv.setLayoutManager(gm);

    vm.getDocumentos().observe(getViewLifecycleOwner(), new Observer<ArrayList<DocumentoVista>>() {
      @Override
      public void onChanged(ArrayList<DocumentoVista> documentos) {
        rv.setAdapter(new DocumentoAdapter(getContext(), documentos,getLayoutInflater()));
      }
    });

    binding.btnDocumentoNuevo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        String directorio = requireContext().getFilesDir().toString();
        Bundle bundle = new Bundle();
        bundle.putString("directorio",directorio);
        Navigation.findNavController(view).navigate(R.id.nav_NuevoDocumentoFragment,bundle);
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