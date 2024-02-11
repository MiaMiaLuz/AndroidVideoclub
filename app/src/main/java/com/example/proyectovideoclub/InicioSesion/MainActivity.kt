package com.example.proyectovideoclub.InicioSesion

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.R
import com.example.proyectovideoclub.Vistas_Trabajador.DialogAddPeliculas
import com.example.proyectovideoclub.Vistas_Trabajador.FragmentVista
import com.example.proyectovideoclub.Vistas_Usuario.FragmentVistaUsuario

class MainActivity : AppCompatActivity() , conexion{

    private var usuarioActivo =  Usuario()
    private var idFragmentActivo : Int = 0
    private var finicio = FragmentInicio()
    private var fragmentTrabajador = FragmentVista(this)
    private var fragmentUser = FragmentVistaUsuario(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame, finicio).commit()
        idFragmentActivo = finicio.id
    }
    override fun repetirValoresInicioSession(finish: Boolean, usuario: Usuario) {
        if(finish){
            var dialog = DialogCrearCuenta(this, finish, usuario)
            dialog.show(supportFragmentManager, "ADD")
        }
    }

    override fun triggerDialog(usuario : Usuario) {
        var dialog = DialogCrearCuenta(this,false, usuario)
        dialog.show(supportFragmentManager, "ADD")
    }
    override fun inicioSesion(usuario: Usuario) {
        this.usuarioActivo = usuario
        var fragmentManager = supportFragmentManager
        var fragmentTransaction = fragmentManager.beginTransaction()
        if(usuario.Trabajador){
            fragmentTransaction.replace(R.id.frame, fragmentTrabajador).commit()
            idFragmentActivo = fragmentTrabajador.id
        } else {
            fragmentTransaction.replace(R.id.frame, fragmentUser).commit()
            fragmentUser.arguments
            idFragmentActivo = fragmentUser.id
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //mirar si se puede ocultar la animacion de ocultar el menu
        if(idFragmentActivo == fragmentUser.id){
            menuInflater.inflate(R.menu.menuusuario, menu)
            supportActionBar?.show()
        } else if(idFragmentActivo == fragmentTrabajador.id){
            menuInflater.inflate(R.menu.menuprincipal, menu)
            supportActionBar?.show()
        } else if(idFragmentActivo == finicio.id) {
            supportActionBar?.hide()
        }
        return true
    }
    override fun triggeradd() {
        var dialog = DialogAddPeliculas()
        dialog.show(supportFragmentManager, "Añadir")
    }
}