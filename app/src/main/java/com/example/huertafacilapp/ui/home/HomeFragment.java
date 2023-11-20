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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.FragmentHomeBinding;
import com.example.huertafacilapp.models.RecordatorioVista;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    HomeViewModel vm;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        vm  = new ViewModelProvider(this).get(HomeViewModel.class);

        RecyclerView rv = binding.listadoRecordatorios;
        GridLayoutManager gm = new GridLayoutManager(getContext(),1, GridLayoutManager.VERTICAL,false);

        rv.setLayoutManager(gm);

        binding.btnNuevoRecordatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nuevoRecordatorioFragment);
            }
        });

        vm.getRecordatorios().observe(getViewLifecycleOwner(),new Observer<ArrayList<RecordatorioVista>>(){
            @Override
            public void onChanged(ArrayList<RecordatorioVista> recordatorios) {
                rv.setAdapter(new HomeAdapter(getContext(),recordatorios,getLayoutInflater()));
            }
        }
        );

        vm.obtenerRecuperatorios();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}