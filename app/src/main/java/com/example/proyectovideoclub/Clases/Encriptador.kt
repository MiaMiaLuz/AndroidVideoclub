package com.example.proyectovideoclub.Clases

import java.lang.StringBuilder
import java.security.MessageDigest

class Encriptador() {
    //Encripta las contraseñas
    fun encriptar(pass : String) : String {
        var passEncriptada = StringBuilder()
        for(a in pass){
            passEncriptada.append(Integer.toHexString(0xff and a.code))
        }
        return passEncriptada.toString()
    }
    //Compara la contraseña introducida con la guardada
    fun comparar(pass: String, passC:String): Boolean {
        var passEn = encriptar(pass)
        return passC == passEn
    }

}