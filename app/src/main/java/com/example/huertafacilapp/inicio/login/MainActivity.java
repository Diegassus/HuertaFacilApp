package com.example.huertafacilapp.inicio.login;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainActivityViewModel vm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        SharedPreferences sp = getApplicationContext().getSharedPreferences("token.xml",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove("token");
        editor.commit();

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);

        binding.btnLogin.setOnClickListener(v -> vm.validarInputs(binding.etCorreo.getText().toString(), binding.etPassword.getText().toString()));

        binding.btnRegistrar.setOnClickListener(v -> vm.registrar());

        setContentView(binding.getRoot());
    }
}