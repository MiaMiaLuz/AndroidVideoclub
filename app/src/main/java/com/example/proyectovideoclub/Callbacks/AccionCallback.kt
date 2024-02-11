package com.example.proyectovideoclub.Callbacks

import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario

interface AccionCallback {
    fun onActionCompleted()
    fun onFailure(errorMessage: String?)
}