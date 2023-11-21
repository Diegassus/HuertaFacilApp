package com.example.huertafacilapp.request;


import com.example.huertafacilapp.models.Amenazas;
import com.example.huertafacilapp.models.Biopreparados;
import com.example.huertafacilapp.models.DocumentoVista;
import com.example.huertafacilapp.models.EnvioRegistro;
import com.example.huertafacilapp.models.EnvioRegistroUpdate;
import com.example.huertafacilapp.models.Login;
import com.example.huertafacilapp.models.Planta;
import com.example.huertafacilapp.models.PlantaListado;
import com.example.huertafacilapp.models.PlantaVista;
import com.example.huertafacilapp.models.RecordatorioVista;
import com.example.huertafacilapp.models.Registro;
import com.example.huertafacilapp.models.Tipo_RecordatorioVista;
import com.example.huertafacilapp.models.Tips;
import com.example.huertafacilapp.models.Usos;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

        @GET("Plantas/Contrarias")
        Call<List<PlantaListado>> listadoContrarias(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("Plantas/ListadoFavoritas")
        Call<List<PlantaListado>> listadoFavoritas(@Header("Authorization") String token);

        @GET("Plantas/")
        Call<Planta> plantaDetalle(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @POST("Usuarios/Favorito")
        Call<Boolean> guardarFavorito(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("plantas/rotaciones")
        Call<List<PlantaListado>> rotaciones(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("plantas/usos")
        Call<List<Usos>> usos(@Header("Authorization") String token, @Query("PlantaId") int PlantaId);

        @GET("usuarios/documentos")
        Call<List<DocumentoVista>> documentos(@Header("Authorization") String token);

        @GET("usuarios/descargar")
        Call<ResponseBody> descargar(@Header("Authorization") String token, @Query("DocumentoId") int DocumentoId);

        @Multipart
        @POST("Usuarios/Documentos")
        Call<Boolean> guardarDocumento(@Header("Authorization") String token, @Part MultipartBody.Part file);

        @Multipart
        @POST("Usuarios/ActualizarDocumentos")
        Call<Boolean> actualizarDocumento(@Header("Authorization") String token, @Part MultipartBody.Part file, @Part("nombreViejo") RequestBody nombreViejo);

        @GET("Usuarios/Recordatorios")
        Call<List<RecordatorioVista>> obtenerRecordatorios(@Header("Authorization")String token);

        @GET("Usuarios/nombresPlantas")
        Call<List<PlantaVista>> nombresPlantas(@Header("Authorization")String token);

        @GET("Usuarios/tiposRecordatorios")
        Call<List<Tipo_RecordatorioVista>> tiposRecordatorios(@Header("Authorization")String token);

        @POST("Usuarios/Recordatorios")
        Call<String> crearRecordatorio(@Header("Authorization")String token, @Body EnvioRegistro envio);

        @GET("Usuarios/Recordatorio")
        Call<RecordatorioVista> verRecordatorio(@Header("Authorization")String token, @Query("idRecordatorio") int idRecordatorio);

        @POST("Usuarios/ActualizarRecordatorio")
        Call<String> actualizarRecordatorio(@Header("Authorization")String token, @Body EnvioRegistroUpdate envio);

        @POST("Usuarios/EliminarRecordatorio")
        Call<String> eliminarRecordatorio(@Header("Authorization")String token, @Query("RecordatorioId") int RecordatorioId);

        @GET("Plantas/tips")
        Call<List<Tips>> obtenerTips(@Header("Authorization")String token, @Query("PlantaId") int PlantaId);

        @GET("Plantas/usos")
        Call<List<Usos>> obtenerUsos(@Header("Authorization")String token, @Query("PlantaId") int PlantaId);

        @GET("plantas/bonificadores")
        Call<List<Biopreparados>> obtenerBonificadores(@Header("Authorization")String token, @Query("PlantaId") int PlantaId);

        @GET("plantas/biopreparados")
        Call<List<Biopreparados>> obtenerCuras(@Header("Authorization")String token, @Query("AmenazaId") int AmenazaId);

        @GET("plantas/amenazas")
        Call<List<Amenazas>> obtenerAmenazas(@Header("Authorization")String token, @Query("PlantaId") int PlantaId);

    }
}