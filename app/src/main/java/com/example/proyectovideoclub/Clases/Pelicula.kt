package com.example.proyectovideoclub.Clases

import java.io.Serializable
import java.sql.Time

class Pelicula : Serializable {
    var ID : Int = 0
    lateinit var titulo : String
    lateinit var duracion : Time
    var year : Int = 0
    lateinit var portada : String
    var ID_director : Int = 0
    var disponible : Boolean = true
    var imagen : Int = 0

    constructor(
        ID: Int,
        titulo: String,
        duracion: Time,
        año: Int,
        portada: String,
        ID_director: Int,
        disponible: Boolean,
    ) {
        this.ID = ID
        this.titulo = titulo
        this.duracion = duracion
        this.year = año
        this.portada = portada
        this.ID_director = ID_director
        this.disponible = disponible
    }

    override fun toString(): String {
        return "$titulo"
    }


}