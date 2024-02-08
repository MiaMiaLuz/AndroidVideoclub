package com.example.proyectovideoclub.InicioSesion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R

class MainActivity : AppCompatActivity() , conexion{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Iniciar sesion
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        var finicio = FragmentInicio()
        fragmentTransaction.replace(R.id.frame, finicio).commit()
        val lc = PeliculaController()
        lc.getPeliculas()
        lc.getPelicula(5)

    }
    override fun repetirValoresInicioSession(finish: Boolean) {
        if(finish){
            var dialog = DialogCrearCuenta(finish)
            dialog.show(supportFragmentManager, "ADD")
        }
    }

    override fun triggerDialog() {
        var dialog = DialogCrearCuenta(false)
        dialog.show(supportFragmentManager, "ADD")
    }
}