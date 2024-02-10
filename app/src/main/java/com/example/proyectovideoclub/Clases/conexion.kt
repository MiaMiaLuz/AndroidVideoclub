package com.example.proyectovideoclub.Clases

interface conexion {
    fun repetirValoresInicioSession(finish : Boolean, usuario: Usuario)
    fun triggerDialog(usuario:Usuario)
    fun inicioSesion(usuario:Usuario)
    fun triggeradd()
}