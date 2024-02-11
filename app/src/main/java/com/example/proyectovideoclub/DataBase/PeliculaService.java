package com.example.proyectovideoclub.DataBase;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.proyectovideoclub.Clases.Alquiler;
import com.example.proyectovideoclub.Clases.Director;
import com.example.proyectovideoclub.Clases.Pelicula;
import com.example.proyectovideoclub.Clases.Respuesta;
import com.example.proyectovideoclub.Clases.Usuario;
import com.google.gson.Gson;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculaService {
    private conexionBBDD con = null;
    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://10.0.2.2/app-clase/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    //USUARIOS
    //crear usuario nuevo
    public void createUsuario(Usuario usuario) {
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Log.d(TAG, "JSON enviado: " + json);

        getRetrofit().create(PeliculaDAO.class).createUsuario(usuario).enqueue(
                new Callback<Usuario>() {
                    @Override
                    public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                        Log.d("TAG", "RESPONSE: " + response);
                    }
                    @Override
                    public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                        Log.d("TAG", "Error");
                        Log.d("TAG", "ERROR: " + t.getMessage());
                    }
                }
        );
    }
    public Call<Usuario> getUsuario(String nombre) {
        return getRetrofit().create(PeliculaDAO.class).getUsuario(nombre);
    }
    public Call<List<Usuario>> getUsuarios()  {
        return getRetrofit().create(PeliculaDAO.class).getUsuarios();
    }

    //PELICULAS
    // Método para insertar una pelicula
    public Call<Respuesta> createPelicula(Pelicula pelicula) {
        return getRetrofit().create(PeliculaDAO.class).createPelicula(pelicula);
    }

    //Método para ver todos las pelicuas
    public Call<List<Pelicula>> getPeliculas(String dni)  {
        return getRetrofit().create(PeliculaDAO.class).getPeliculas(dni);
    }
    //Método para ver una pelicula por ID
    public Call<Pelicula> getPelicula(int id) {
        return getRetrofit().create(PeliculaDAO.class).getPelicula(id);
    }
    //Metodo para borrar una pelicula
    public Call<Pelicula> borrarPelicula(int id) {
        return getRetrofit().create(PeliculaDAO.class).borrarPelicula(id);
    }

    //DIRECTORES
    //Obtener los directores
    public Call<List<Director>> getDirectores()  {
        return getRetrofit().create(PeliculaDAO.class).getDirectores();
    }

    //ALQUILERES
    //Obtener los alquileres
    public Call<List<Alquiler>>getAlquileres()  {
        return getRetrofit().create(PeliculaDAO.class).getAlquileres();
    }
    public Call<Respuesta> extenderDevolucion(int idAlquiler) {
        return getRetrofit().create(PeliculaDAO.class).extenderDevolucion(idAlquiler);
    }
}
