package com.example.proyectovideoclub.DataBase;

import com.example.proyectovideoclub.Clases.Director;
import com.example.proyectovideoclub.Clases.Pelicula;
import com.example.proyectovideoclub.Clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface PeliculaDAO {
    //USUARIOS
    //crear nuevo usuario
    @POST("crearUsuario.php")
    public Call<Usuario> createUsuario(@Body Usuario usuario);
    //Cambiar para que sea por parametro y poder usarlo para filtar por el DNI
    @GET("getUsuario.php")
    public Call<Usuario> getUsuario(@Query("Login") String nombre);
    @GET("getUsuarios.php")
    public Call<List<Usuario>>getUsuarios();

    //PELICULAS
    // Método para insertar una pelicula
    @POST("crearPelicula.php")
    public Call<Pelicula> createPelicula(@Body Pelicula pelicula);

    // Método para obtener todas los libros
    @GET("getPeliculas.php")
    public Call<List<Pelicula>>getPeliculas();

    //DIRECTORES
    //Obtener la lista de directores
    @GET("getDirectores.php")
    public Call<List<Director>>getDirectores();

    // Método para obtener un libro por su ID
    @GET("getLibro.php")
    public Call<Pelicula> getPelicula(@Query("id") int id);


}
