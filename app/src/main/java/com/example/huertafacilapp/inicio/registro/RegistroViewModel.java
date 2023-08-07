package com.example.huertafacilapp.inicio.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.huertafacilapp.models.Registro;
import com.example.huertafacilapp.request.ApiClient;
import com.example.huertafacilapp.ui.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            Toast.makeText(context, "Ambas contraseñas deben ser iguales", Toast.LENGTH_SHORT).show();
        }else{

            Toast.makeText(context, "Cargando...", Toast.LENGTH_SHORT).show();

            Registro registro = new Registro(correo, clave, isSur);
            ApiClient.IEndpoint endpoint = ApiClient.getApi();
            Call<String> call = endpoint.registro(registro);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        if(response.body()!=null){
                            SharedPreferences sp = context.getSharedPreferences("token.xml",0);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("token","Bearer "+response.body());
                            editor.commit();
                            Intent intent = new Intent(context, HomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(context, "Error "+t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
