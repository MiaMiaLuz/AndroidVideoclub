package com.example.proyectovideoclub.DataBase

import com.example.proyectovideoclub.Clases.Usuario

interface conexionBBDD {
    fun mandarUsuario(usuario: Usuario)
}