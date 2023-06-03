package com.example.huertafacilapp.inicio.registro;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class RegistroViewModel extends AndroidViewModel {
    private Context context;

    public RegistroViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void validarInputs(String correo, String clave, String repetida, boolean isSur){
        if(correo.isEmpty() || clave.isEmpty() || repetida.isEmpty()){
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if (!correo.contains("@")) {
            Toast.makeText(context, "Debe brindar un correo valido", Toast.LENGTH_SHORT).show();
        }else if(clave.length()<6){
            Toast.makeText(context, "La contraseña debe tener 6 o mas caracteres", Toast.LENGTH_SHORT).show();
        } else if (!clave.equals(repetida)) {
            Toast.makeText(context, "La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Creando usuario", Toast.LENGTH_SHORT).show();
            // Hacer llamado al ApiClient al registro
        }
    }
}
