package com.example.huertafacilapp.ui.home.detalle;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentDetalleRecordatorioBinding;
import com.example.huertafacilapp.models.PlantaVista;
import com.example.huertafacilapp.models.Tipo_RecordatorioVista;

public class DetalleRecordatorioFragment extends Fragment {

  private DetalleRecordatorioViewModel mViewModel;
  private FragmentDetalleRecordatorioBinding binding;

  public static DetalleRecordatorioFragment newInstance() {
    return new DetalleRecordatorioFragment();
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {
    binding = FragmentDetalleRecordatorioBinding.inflate(inflater, container, false);
    mViewModel = new ViewModelProvider(this).get(DetalleRecordatorioViewModel.class);

    mViewModel.getRecordatorio().observe(getViewLifecycleOwner(), recordatorio -> {
      binding.etdEvento.setText(recordatorio.getEvento().split("T")[0].replace(":","/"));
    });

    mViewModel.getRecordatorios().observe(getViewLifecycleOwner(), recordatorios -> {
      ArrayAdapter<Tipo_RecordatorioVista> adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_dropdown_item,recordatorios);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      binding.spinnerTarea.setAdapter(adapter);

      int deseado = mViewModel.encontrarPosicionPorIdTipoRecordatorio(recordatorios);
      binding.spinnerTarea.setSelection(deseado);
      binding.spinnerTarea.setEnabled(false);
    });

    mViewModel.getPlantas().observe(getViewLifecycleOwner(), plantas -> {
      ArrayAdapter<PlantaVista> adapter = new ArrayAdapter<>(requireContext(),android.R.layout.simple_spinner_dropdown_item,plantas);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      binding.spinnerPlantas.setAdapter(adapter);

      int deseado = mViewModel.encontrarPosicionPorIdPlanta(plantas);
      binding.spinnerPlantas.setSelection(deseado);
      binding.spinnerPlantas.setEnabled(false);
    });

    binding.cbEditable.setOnCheckedChangeListener((buttonView, isChecked) -> {
      if(isChecked){
        binding.spinnerTarea.setEnabled(true);
        binding.spinnerPlantas.setEnabled(true);
        binding.btnEliminar.setEnabled(false);
        binding.etdEvento.setEnabled(true);
        binding.btnModificarRecordatorio.setEnabled(true);
      }else{
        binding.spinnerTarea.setEnabled(false);
        binding.spinnerPlantas.setEnabled(false);
        binding.btnEliminar.setEnabled(true);
        binding.etdEvento.setEnabled(false);
        binding.btnModificarRecordatorio.setEnabled(false);
      }
    });

    binding.btnModificarRecordatorio.setOnClickListener(view -> {
      PlantaVista planta = (PlantaVista) binding.spinnerPlantas.getSelectedItem();
      Tipo_RecordatorioVista tipo = (Tipo_RecordatorioVista) binding.spinnerTarea.getSelectedItem();
      mViewModel.crearRecordatorio(planta.getId(),tipo.getId(),binding.etdEvento.getText().toString());
    });

    mViewModel.recuperarRecordatorio(getArguments());

    binding.btnEliminar.setOnClickListener(view -> {
      mViewModel.eliminarRecordatorio();
      Navigation.findNavController(view).navigate(R.id.nav_home);
    });

    return binding.getRoot();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = new ViewModelProvider(this).get(DetalleRecordatorioViewModel.class);
    // TODO: Use the ViewModel
  }

}