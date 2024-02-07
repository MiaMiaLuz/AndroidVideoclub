package com.example.proyectovideoclub.Clases

import java.sql.Date

class Director {
    private var IDDirector : Int = 0
    private lateinit var nombre : String
    private lateinit var fechaNac : Date

    constructor(IDDirector: Int, nombre: String, fechaNac: Date) {
        this.IDDirector = IDDirector
        this.nombre = nombre
        this.fechaNac = fechaNac
    }
}