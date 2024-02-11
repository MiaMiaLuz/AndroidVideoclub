package com.example.proyectovideoclub.Clases

import java.io.Serializable

class Director : Serializable{
    var IDDirector : Int = 0
    var nombre : String
    var fechaNac : String

    constructor(IDDirector: Int, nombre: String, fechaNac: String) {
        this.IDDirector = IDDirector
        this.nombre = nombre
        this.fechaNac = fechaNac
    }

    override fun toString(): String {
        return nombre
    }

}