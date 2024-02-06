package com.example.proyectovideoclub.Clases

import java.io.Serializable
import java.sql.Time

class Pelicula : Serializable {
    private var ID : Int = 0
    private lateinit var titulo : String
    private lateinit var duracion : Time
    private var year : Int = 0
    private lateinit var portada : String
    private var ID_director : Int = 0
    private var disponible : Boolean = true

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