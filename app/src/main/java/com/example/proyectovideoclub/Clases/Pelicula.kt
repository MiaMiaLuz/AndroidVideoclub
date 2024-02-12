package com.example.proyectovideoclub.Clases

import java.io.Serializable
import java.sql.Time

class Pelicula : Serializable {
    var ID : Int = 0
    var titulo : String
    var duracion : String
    var year : Int = 0
    var portada : ByteArray? = null
    var ID_director : Int = 0
    var disponible : Boolean = true
    var imagen : Int = 0
    var nombreDirector : String
    var ID_AlquilerVigente : Int = 0
    //para el layout del usuario
    var check : Boolean = false
    constructor(
        ID: Int,
        titulo: String,
        duracion: String,
        año: Int,
        portada: ByteArray,
        ID_director: Int,
        disponible: Boolean,
        nombreDirector: String,
    ) {
        this.ID = ID
        this.titulo = titulo
        this.duracion = duracion
        this.year = año
        this.portada = portada
        this.ID_director = ID_director
        this.disponible = disponible
        this.nombreDirector = nombreDirector
    }

    constructor(
        titulo: String,
        duracion: String,
        year: Int,
        ID_director: Int,
        disponible: Boolean,
        nombreDirector: String
    ) {
        this.titulo = titulo
        this.duracion = duracion
        this.year = year
        this.ID_director = ID_director
        this.disponible = disponible
        this.nombreDirector = nombreDirector
    }

    override fun toString(): String {
        return "$titulo"
    }
}