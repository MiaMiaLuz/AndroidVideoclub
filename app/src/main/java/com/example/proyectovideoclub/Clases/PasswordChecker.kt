package com.example.proyectovideoclub.Clases

import java.lang.StringBuilder

class PasswordChecker() {
    //Encripta las contraseñas
    private final val PASSWORDLENGTH : Int = 7
    private final val SPECIALCHAR : String = "*_-/&$!"
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

    fun comprobar(pass: String) : Boolean{
        if(pass.length < PASSWORDLENGTH){
            return false
        } else {
            for(caracter in SPECIALCHAR) {
                if (pass.contains(caracter)) {
                    return true
                }
            }
            return false
        }
    }

}