package com.example.proyectovideoclub.Callbacks

import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario

interface PeliculaCallback {
    fun onPeliculaReceived(pelicula: Pelicula?)
    fun onFailure(errorMessage: String?)
}