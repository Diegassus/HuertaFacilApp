package com.example.huertafacilapp.request;


import com.example.huertafacilapp.models.Login;
import com.example.huertafacilapp.models.Planta;
import com.example.huertafacilapp.models.PlantaListado;
import com.example.huertafacilapp.models.Registro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

        @GET("usuarios/Favorito")
        Call<Boolean> esFavorito(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("Plantas/ListadoPrincipal")
        Call<List<PlantaListado>> listadoPrincipal(@Header("Authorization") String token);

        @GET("Plantas/ListadoFavoritas")
        Call<List<PlantaListado>> listadoFavoritas(@Header("Authorization") String token);

        @GET("Plantas/")
        Call<Planta> plantaDetalle(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @POST("Usuarios/Favorito")
        Call<Boolean> guardarFavorito(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("plantas/rotaciones")
        Call<List<PlantaListado>> rotaciones(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);
    }
}
