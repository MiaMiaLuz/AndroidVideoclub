package com.example.proyectovideoclub.InicioSesion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R
import com.example.proyectovideoclub.Vistas_Trabajador.DialogAddPeliculas
import com.example.proyectovideoclub.Vistas_Trabajador.FragmentVista
import com.example.proyectovideoclub.Vistas_Usuario.FragmentVistaUsuario

class MainActivity : AppCompatActivity() , conexion{

    private var usuarioActivo =  Usuario()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Iniciar sesion
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var finicio = FragmentInicio()
        fragmentTransaction.replace(R.id.frame, finicio).commit()

    }
    override fun repetirValoresInicioSession(finish: Boolean, usuario: Usuario) {
        if(finish){
            var dialog = DialogCrearCuenta(finish, usuario)
            dialog.show(supportFragmentManager, "ADD")
        }
    }

    override fun triggerDialog(usuario : Usuario) {
        var dialog = DialogCrearCuenta(false, usuario)
        dialog.show(supportFragmentManager, "ADD")
    }

    override fun inicioSesion(usuario: Usuario) {
        this.usuarioActivo = usuario
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        if(usuario.Trabajador){
           var fragmentUser = FragmentVista()
            fragmentTransaction.replace(R.id.frame, fragmentUser).commit()
        } else {
            var fragmentUser = FragmentVistaUsuario()
            fragmentTransaction.replace(R.id.frame, fragmentUser).commit()
        }

    }

    override fun triggeradd() {
        var dialog = DialogAddPeliculas()
        dialog.show(supportFragmentManager, "Añadir")
    }
}