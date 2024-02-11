package com.example.proyectovideoclub.Clases

import java.io.Serializable
import java.sql.Date

class Alquiler : Serializable {
    var IDAlquiler : Int = 0
    var IDPelicula: Int = 0
    var IDUsuario: Int = 0
    var fechaAlquiler : String
    var fechaDevolucion : String
    var extendido : Boolean = false
    var dni : String = ""

    constructor(
        IDAlquiler: Int,
        IDPelicula: Int,
        IDUsuario: Int,
        fechaAlquiler: String,
        fechaDevolucion: String,
        dni : String
    ) {
        this.IDAlquiler = IDAlquiler
        this.IDPelicula = IDPelicula
        this.IDUsuario = IDUsuario
        this.fechaAlquiler = fechaAlquiler
        this.fechaDevolucion = fechaDevolucion
        this.dni = dni
    }

    override fun toString(): String {
        return "fechaAlquiler = $fechaAlquiler, fechaDevolucion = $fechaDevolucion, extendido = $extendido)"
    }


}