package com.example.huertafacilapp.inicio.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.huertafacilapp.R;
import com.example.huertafacilapp.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private ActivityRegistroBinding binding;
    private RegistroViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());

        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vm.validarInputs(binding.etCorreo.getText().toString(), binding.etPassword.getText().toString(),binding.etRepetida.getText().toString(), binding.cbSur.isChecked());
            }
        });

        setContentView(binding.getRoot());
    }
}