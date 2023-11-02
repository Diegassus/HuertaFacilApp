package com.example.huertafacilapp.ui.home;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.huertafacilapp.databinding.FragmentHomeBinding;
public class HomeFragment extends Fragment {
    HomeViewModel vm;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm  = new ViewModelProvider(this).get(HomeViewModel.class);
        vm.init(requireContext());

        vm.getClimaCargado().observe(getViewLifecycleOwner(), value -> {
            if (!value) {
                binding.tvMaxima.setVisibility(View.INVISIBLE);
                binding.tvMinima.setVisibility(View.INVISIBLE);
                binding.tvActual.setVisibility(View.INVISIBLE);
                binding.tvNombre1.setVisibility(View.INVISIBLE);
                binding.tvNombre2.setVisibility(View.INVISIBLE);
                binding.tvNombre3.setVisibility(View.INVISIBLE);
                binding.tvPresion.setVisibility(View.INVISIBLE);
                binding.tvProbabilidad.setVisibility(View.INVISIBLE);
                binding.tvTextoMaxima.setVisibility(View.INVISIBLE);
                binding.tvTextoMinima.setVisibility(View.INVISIBLE);
                binding.tvEstado.setVisibility(View.INVISIBLE);
                binding.tvViento.setVisibility(View.INVISIBLE);
                binding.tvInformacionClima.setVisibility(View.VISIBLE);
            }else {
                binding.tvInformacionClima.setVisibility(View.INVISIBLE);
                binding.tvMaxima.setVisibility(View.VISIBLE);
                binding.tvMinima.setVisibility(View.VISIBLE);
                binding.tvActual.setVisibility(View.VISIBLE);
                binding.tvNombre1.setVisibility(View.VISIBLE);
                binding.tvNombre2.setVisibility(View.VISIBLE);
                binding.tvNombre3.setVisibility(View.VISIBLE);
                binding.tvPresion.setVisibility(View.VISIBLE);
                binding.tvProbabilidad.setVisibility(View.VISIBLE);
                binding.tvTextoMaxima.setVisibility(View.VISIBLE);
                binding.tvTextoMinima.setVisibility(View.VISIBLE);
                binding.tvEstado.setVisibility(View.VISIBLE);
                binding.tvViento.setVisibility(View.VISIBLE);
            }
        });

        binding.tvInformacionClima.setOnClickListener(v -> {
            vm.cargarClima(true);
            requestLocationPermissions();
        });

        vm.getConsejo().observe(getViewLifecycleOwner(), value -> {
            binding.tvRecomendacion.setText(value);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void requestLocationPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(requireContext(), ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000);
            } else {
                vm.obtenerClima();
            }
        } else {
            // mostrar que no se pudo cargar datos climaticos
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                vm.obtenerClima();
            }
        }
    }

}