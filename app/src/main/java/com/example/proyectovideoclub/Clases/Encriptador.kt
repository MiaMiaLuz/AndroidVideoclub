package com.example.proyectovideoclub.Clases

import java.security.MessageDigest

class Encriptador() {
    fun encriptar(pass : String) : String {
        val encriptador = MessageDigest.getInstance("MD5")
        encriptador.update(pass.toByteArray())
        val datos = encriptador.digest()
        val hexa = StringBuilder()
        for (byte in hexa) {
            val hex = Integer.toHexString(0xff and byte.toInt())
            if (hex.length == 1) hexa.append('0')
            hexa.append(hex)
        }
        return hexa.toString()
    }

    fun desEncriptar(pass: String, ): Boolean {
        val passEncriptada = encriptar(pass)
        return pass == passEncriptada
    }
}