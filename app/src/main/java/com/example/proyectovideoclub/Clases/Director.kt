package com.example.proyectovideoclub.Clases

import java.io.Serializable

class Director : Serializable{
    private var IDDirector : Int = 0
    private lateinit var nombre : String
    private lateinit var fechaNac : String

    constructor(IDDirector: Int, nombre: String, fechaNac: String) {
        this.IDDirector = IDDirector
        this.nombre = nombre
        this.fechaNac = fechaNac
    }

    override fun toString(): String {
        return "'$nombre'"
    }


}