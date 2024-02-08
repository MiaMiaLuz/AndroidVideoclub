package com.example.proyectovideoclub.DataBase;

import android.util.Log;

import androidx.annotation.NonNull;
import com.example.proyectovideoclub.Clases.Pelicula;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaService {
    // Instancia de Retrofit
    //La url es la correspondiente a la localización del servidor.
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/app-clase/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    // Método para insertar un libro
    public void createPelicula(Pelicula pelicula) {
        getRetrofit().create(PeliculaDAO.class).createPelicula(pelicula).enqueue(
                new Callback<Pelicula>() {
                    @Override
                    public void onResponse(@NonNull Call<Pelicula> call, @NonNull Response<Pelicula> response) {
                        Log.d("TAG", "RESPONSE: " + response);
                    }

                    @Override
                    public void onFailure(@NonNull Call<Pelicula> call, @NonNull Throwable t) {
                        Log.d("TAG", "Error");
                        Log.d("TAG", "ERROR: " + t.getMessage());
                    }
                }
        );
    }

    //Método para ver todos los libros
    public Call<List<Pelicula>> getPeliculas()  {
        return getRetrofit().create(PeliculaDAO.class).getPeliculas();
    }

    //Método para ver un libro por ID
    public Call<Pelicula> getPelicula(int id) {
        return getRetrofit().create(PeliculaDAO.class).getPelicula(id);
    }
}
