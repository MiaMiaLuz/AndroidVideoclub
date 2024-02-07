package com.example.proyectovideoclub.Clases

import java.io.Serializable
import java.sql.Date

class Alquiler : Serializable {
    private var IDAlquiler : Int = 0
    private var IDPelicula: Int = 0
    private var IDUsuario: Int = 0
    private lateinit var fechaAlquiler : Date
    private lateinit var fechaDevolucion : Date
    private var extendido : Boolean = false

    constructor(
        IDAlquiler: Int,
        IDPelicula: Int,
        IDUsuario: Int,
        fechaAlquiler: Date,
        fechaDevolucion: Date,
    ) {
        this.IDAlquiler = IDAlquiler
        this.IDPelicula = IDPelicula
        this.IDUsuario = IDUsuario
        this.fechaAlquiler = fechaAlquiler
        this.fechaDevolucion = fechaDevolucion
    }

    override fun toString(): String {
        return "fechaAlquiler = $fechaAlquiler, fechaDevolucion = $fechaDevolucion, extendido = $extendido)"
    }


}