package com.example.proyectovideoclub.Clases

import java.io.Serializable

class Usuario: Serializable{
    var ID : Int = 0
    var DNI : String = ""
    var Nombre : String = ""
    var Login : String = ""
    var Pass : String = ""
    var Trabajador : Boolean = false
    var Bloqueado : Boolean = false

    constructor(ID: Int, DNI: String, Nombre: String, Login: String, Pass: String) {
        this.ID = ID
        this.DNI = DNI
        this.Nombre = Nombre
        this.Login = Login
        this.Pass = Pass
    }
    constructor()

    override fun toString(): String {
        return "Usuario(ID=$ID, DNI='$DNI', Nombre='$Nombre', Login='$Login', Pass='$Pass', Trabajador=$Trabajador, Bloqueado=$Bloqueado)"
    }


}