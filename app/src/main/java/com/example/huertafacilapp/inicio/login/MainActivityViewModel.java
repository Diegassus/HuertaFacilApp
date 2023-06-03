package com.example.huertafacilapp.inicio.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.huertafacilapp.inicio.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    public void validarInputs(String correo, String clave){
        if(correo.isEmpty() || clave.isEmpty()){
            Toast.makeText(context,"Los campos no pueden estar vacios",Toast.LENGTH_SHORT).show();
        }else if(!correo.contains("@") || clave.length()<6){
                Toast.makeText(context,"Clave o correo incorrectos",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(context,"Bienvenido",Toast.LENGTH_SHORT).show();
                // Hacer llamado al ApiClient al login
            }
    }

    public void registrar(){
        Intent intent = new Intent(context, RegistroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void recuperar(){
        Toast.makeText(context, "Viajar a recuperacion", Toast.LENGTH_SHORT).show();
        // implementar logica de recuperacion
    }
}
