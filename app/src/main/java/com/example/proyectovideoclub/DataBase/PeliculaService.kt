package com.example.api_kotlin

import android.util.Log
import com.example.proyectovideoclub.DataBase.PeliDAO
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PeliculaService {

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://172.28.96.1/app-clase/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createPeli(peli : Pelicula) {
        getRetrofit().create(PeliDAO::class.java).createPeli(peli).enqueue(object : Callback<Pelicula> {
            override fun onResponse(call: Call<Pelicula>, response: Response<Pelicula>) {
                Log.d("TAG", "RESPONSE: $response")
            }
            override fun onFailure(call: Call<Pelicula>, t: Throwable) {
                Log.d("TAG", "Error")
                Log.d("TAG", "ERROR: $t")
            }
        })
    }
    fun createUser(user: User) {
        getRetrofit().create(PeliDAO::class.java).createUser(user).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                Log.d("TAG", "RESPONSE: $response")
            }
            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d("TAG", "Error")
                Log.d("TAG", "ERROR: $t")
            }
        })
    }

    fun getPelis() : Call<List<Pelicula>> {
        return getRetrofit().create(PeliDAO::class.java).getPelis()
    }

    fun getLibro(id: Int) : Call<Pelicula> {
        return getRetrofit().create(PeliDAO::class.java).getPelis(id)
    }

}