package com.example.proyectovideoclub.DataBase

import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PeliDAO {
    @POST("crearPeli.php")
    fun createPeli(@Body peli: Pelicula): Call<Pelicula>

    @GET("getPelis.php")
    fun getPelis(): Call<List<Pelicula>>

    @GET("getPeli.php")
    fun getPeli(@Query("id") id: Int): Call<Pelicula>

    @POST("crearUser.php")
    fun createUser(@Body usuario: Usuario): Call<Usuario>
}