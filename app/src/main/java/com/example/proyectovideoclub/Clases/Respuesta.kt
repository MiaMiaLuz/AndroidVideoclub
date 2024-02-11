package com.example.proyectovideoclub.Clases

import java.io.Serializable

class Respuesta : Serializable{
    var message : String

    constructor(message: String) {
        this.message = message
    }
}