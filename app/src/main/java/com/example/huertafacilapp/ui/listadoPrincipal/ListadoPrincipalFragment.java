package com.example.huertafacilapp.ui.listadoPrincipal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.huertafacilapp.databinding.FragmentListadoPrincipalBinding;
import com.example.huertafacilapp.models.PlantaListado;

import java.util.ArrayList;

public class ListadoPrincipalFragment extends Fragment {
    private ListadoPrincipalViewModel vm;
    private FragmentListadoPrincipalBinding binding;

    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState) {

        vm = new ViewModelProvider(this).get(ListadoPrincipalViewModel.class);
        binding = FragmentListadoPrincipalBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView rv = binding.listaPrincipal;
        GridLayoutManager gm = new GridLayoutManager(
                                     getContext(),
                            1,
                                     GridLayoutManager.VERTICAL,
                          false);
        rv.setLayoutManager(gm);

        vm.getPlantas().observe(getViewLifecycleOwner(), new Observer<ArrayList<PlantaListado>>() {
            @Override
            public void onChanged(ArrayList<PlantaListado> plantas) {
                rv.setAdapter(
                    new ListadoPrincipalAdapter(getContext(),plantas,getLayoutInflater())
                );
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}