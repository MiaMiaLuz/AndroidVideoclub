package com.example.proyectovideoclub.InicioSesion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectovideoclub.Clases.Ususario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.R

class MainActivity : AppCompatActivity(), View.OnClickListener , conexion{

    private lateinit var fondo : androidx.constraintlayout.widget.ConstraintLayout
    private lateinit var BotonCliente : Button
    private lateinit var BotonTrabajador : Button
    private lateinit var NombreUsuario : EditText
    private lateinit var Contrasenna : EditText
    private lateinit var CrearCuenta : Button
    private lateinit var InicioSesion : Button
    private var usuario = Ususario()
    private lateinit var dialog : DialogCrearCuenta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fondo = findViewById(R.id.FondoMain)
        BotonCliente = findViewById(R.id.BotonCliente)
        BotonTrabajador = findViewById(R.id.BotonTrabajador)
        NombreUsuario = findViewById(R.id.NombreDeUsuario)
        Contrasenna = findViewById(R.id.Contrasenna)
        CrearCuenta = findViewById(R.id.BotonCrearCuenta)
        InicioSesion = findViewById(R.id.BotonIniciarSesion)

        CrearCuenta.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.BotonCliente){
            usuario.Trabajador = false
        } else if(v?.id == R.id.BotonTrabajador){
            usuario.Trabajador = true
        } else if(v?.id == R.id.BotonCrearCuenta){
            var dialog = DialogCrearCuenta(false)
            dialog.show(supportFragmentManager, "ADD")
        } else if(v?.id == R.id.BotonIniciarSesion){
            if(NombreUsuario.text.isBlank() || Contrasenna.text.isBlank()){
                Toast.makeText(this,
                    getString(R.string.el_nombre_de_usuario_o_la_contrase_a_est_n_en_blanco), Toast.LENGTH_SHORT).show()
            } else {

            }
        }
    }
    override fun repetirValoresInicioSession(finish: Boolean) {
        if(finish){
            var dialog = DialogCrearCuenta(finish)
            dialog.show(supportFragmentManager, "ADD")
        }
    }
}