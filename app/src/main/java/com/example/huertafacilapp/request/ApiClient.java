package com.example.huertafacilapp.request;


import com.example.huertafacilapp.models.Login;
import com.example.huertafacilapp.models.Registro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ApiClient {
    private static final String PATH = "";
    private static IEndpoint servicio;
    public static IEndpoint getApi(){ // Crea instancia de ApiClient
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        return servicio = retrofit.create(IEndpoint.class);
    }

    public interface IEndpoint { // Endpoints de la API
        @POST("usuarios/registro")
        Call<String> registro(@Body Registro registro);

        @POST("usuarios/login")
        Call<String> login(@Body Login login);
    }
}
