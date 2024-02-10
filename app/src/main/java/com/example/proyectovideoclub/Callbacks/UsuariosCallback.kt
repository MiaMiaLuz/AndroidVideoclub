package com.example.proyectovideoclub.Callbacks

import com.example.proyectovideoclub.Clases.Usuario

interface UsuariosCallback {
    fun onUsuariosReceived(usuarios: ArrayList<Usuario?>?)
    fun onFailure(errorMessage: String?)

}