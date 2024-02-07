package com.example.proyectovideoclub.Clases

class Ususario {
    var ID : Int = 0
    lateinit var DNI : String
    lateinit var Nombre : String
    lateinit var Login : String
    lateinit var Pass : String
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


}