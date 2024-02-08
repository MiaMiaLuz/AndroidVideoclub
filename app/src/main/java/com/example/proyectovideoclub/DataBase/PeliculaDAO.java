package com.example.proyectovideoclub.DataBase;

import com.example.proyectovideoclub.Clases.Pelicula;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PeliculaDAO {
    // Método para insertar un libro
    @POST("crearLibro.php")
    public Call<Pelicula> createPelicula(@Body Pelicula pelicula);

    // Método para obtener todas los libros
    @GET("getLibros.php")
    public Call<List<Pelicula>> getPeliculas();

    // Método para obtener un libro por su ID
    @GET("getLibro.php")
    public Call<Pelicula> getPelicula(@Query("id") int id);
}
