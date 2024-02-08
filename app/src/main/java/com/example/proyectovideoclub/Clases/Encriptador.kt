package com.example.proyectovideoclub.Clases

import java.lang.StringBuilder
import java.security.MessageDigest

class Encriptador() {
    fun encriptar(pass : String) : String {
        var passEncriptada = StringBuilder()
        for(a in pass){
            passEncriptada.append(Integer.toHexString(0xff and a.code))
        }
        return passEncriptada.toString()
    }


    fun comparar(pass: String): Boolean {
        var passEncriptada = StringBuilder()
        for(a in pass){
            passEncriptada.append(Integer.toHexString(0xff and a.code))
        }

        return true
    }
}