package com.example.huertafacilapp.ui.home.nuevo;

import static android.R.layout.simple_spinner_dropdown_item;
import static android.R.layout.simple_spinner_item;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentNuevoRecordatorioBinding;
import com.example.huertafacilapp.models.PlantaVista;
import com.example.huertafacilapp.models.RecordatorioVista;
import com.example.huertafacilapp.models.Tipo_RecordatorioVista;

public class NuevoRecordatorioFragment extends Fragment {

  private NuevoRecordatorioViewModel mViewModel;
  private FragmentNuevoRecordatorioBinding binding;

  public static NuevoRecordatorioFragment newInstance() {
    return new NuevoRecordatorioFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    mViewModel = new ViewModelProvider(this).get(NuevoRecordatorioViewModel.class);
    binding = FragmentNuevoRecordatorioBinding.inflate(inflater, container, false);

    mViewModel.getRecordatorios().observe(getViewLifecycleOwner(), recordatorios -> {
      ArrayAdapter<Tipo_RecordatorioVista> adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_dropdown_item,recordatorios);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      binding.spinnerTarea.setAdapter(adapter);
    });

    mViewModel.getPlantas().observe(getViewLifecycleOwner(), plantas -> {
      ArrayAdapter<PlantaVista> adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_dropdown_item,plantas);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      binding.spinnerPlantas.setAdapter(adapter);
    });

    binding.btnCrearRecordatorio.setOnClickListener(view -> {
      PlantaVista planta = (PlantaVista) binding.spinnerPlantas.getSelectedItem();
      Tipo_RecordatorioVista tipo = (Tipo_RecordatorioVista) binding.spinnerTarea.getSelectedItem();
      mViewModel.crearRecordatorio(planta.getId(),tipo.getId(),binding.etdEvento.getText().toString());
    });

    mViewModel.recuperarDatos();

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(NuevoRecordatorioViewModel.class);
    // TODO: Use the ViewModel
  }

}