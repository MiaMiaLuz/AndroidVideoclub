package com.example.proyectovideoclub.InicioSesion

import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView.Orientation
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.R
import com.example.proyectovideoclub.Vistas_Trabajador.DialogAddPeliculas
import com.example.proyectovideoclub.Vistas_Trabajador.FragmentVista
import com.example.proyectovideoclub.Vistas_Usuario.FragmentVistaUsuario

class MainActivity : AppCompatActivity() , conexion{

    private var usuarioActivo = Usuario()
    private var idFragmentActivo : Int = 0
    private var finicio = FragmentInicio()
    private var fragmentTrabajador = FragmentVista(this)
    private var fragmentUser = FragmentVistaUsuario(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null) {
            var fragmentManager = supportFragmentManager
            var fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.frame, finicio).commit()
            idFragmentActivo = finicio.id
            usuarioActivo?.let { orientacion(it) }
        } else {
            //assert(savedInstanceState?.getSerializable("Usuario") as Usuario != null)
            usuarioActivo = (savedInstanceState?.getSerializable("Usuario") as? Usuario)!!
            idFragmentActivo = savedInstanceState?.getInt("IDActivo")!!
        }
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
    private fun orientacion(usuario: Usuario){
        if(idFragmentActivo == fragmentTrabajador.id || idFragmentActivo == fragmentUser.id) {
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                this.usuarioActivo = usuario
                var fragmentManager = supportFragmentManager
                var fragmentTransaction = fragmentManager.beginTransaction()
                if (usuario.Trabajador) {
                    fragmentTransaction.replace(R.id.frame, fragmentTrabajador).commit()
                    idFragmentActivo = fragmentTrabajador.id
                } else {
                    fragmentTransaction.replace(R.id.frame, fragmentUser).commit()
                    fragmentUser.arguments
                    idFragmentActivo = fragmentUser.id
                }
            } else {
                this.usuarioActivo = usuario
                var fragmentManager = supportFragmentManager
                var fragmentTransaction = fragmentManager.beginTransaction()
                if (usuario.Trabajador) {
                    fragmentTransaction.replace(R.id.frame, fragmentTrabajador).commit()
                    idFragmentActivo = fragmentTrabajador.id
                } else {
                    fragmentTransaction.replace(R.id.frame, fragmentUser).commit()
                    fragmentUser.arguments
                    idFragmentActivo = fragmentUser.id
                }
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (usuarioActivo != null) {
            outState.putSerializable("Usuario", usuarioActivo)
            outState.putInt("IDActivo", idFragmentActivo)
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