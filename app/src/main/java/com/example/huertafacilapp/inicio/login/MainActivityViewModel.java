package com.example.huertafacilapp.inicio.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.huertafacilapp.inicio.registro.RegistroActivity;
import com.example.huertafacilapp.models.Login;
import com.example.huertafacilapp.request.ApiClient;
import com.example.huertafacilapp.ui.HomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
            //Toast.makeText(context, "Cargando...", Toast.LENGTH_SHORT).show();
            Login login = new Login(correo,clave);
            ApiClient.IEndpoint endpoint = ApiClient.getApi();
            Call<String> call = endpoint.login(login);
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
                    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
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
