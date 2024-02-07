package com.example.proyectovideoclub.InicioSesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
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