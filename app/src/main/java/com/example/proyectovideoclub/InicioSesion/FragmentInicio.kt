package com.example.proyectovideoclub.InicioSesion

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.R

class FragmentInicio : Fragment(), View.OnClickListener {

    private var usuario = Usuario()
    private var conexion : conexion? = null
    private lateinit var fondo : androidx.constraintlayout.widget.ConstraintLayout
    private lateinit var BotonCliente : Button
    private lateinit var BotonTrabajador : Button
    private lateinit var NombreUsuario : EditText
    private lateinit var Contrasenna : EditText
    private lateinit var CrearCuenta : Button
    private lateinit var InicioSesion : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = arguments?.getSerializable("usuario") as Usuario
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_inicio, container, false)

        fondo = view.findViewById(R.id.FondoMain)
        BotonCliente = view.findViewById(R.id.BotonCliente)
        BotonTrabajador = view.findViewById(R.id.BotonTrabajador)
        NombreUsuario = view.findViewById(R.id.NombreDeUsuario)
        Contrasenna = view.findViewById(R.id.Contrasenna)
        CrearCuenta = view.findViewById(R.id.BotonCrearCuenta)
        InicioSesion = view.findViewById(R.id.BotonIniciarSesion)

        BotonCliente.setOnClickListener(this)
        BotonTrabajador.setOnClickListener(this)
        CrearCuenta.setOnClickListener(this)
        InicioSesion.setOnClickListener(this)

        return view
    }
    override fun onClick(v: View?) {
        if(v?.id == R.id.BotonCliente){
            usuario.Trabajador = false
        } else if(v?.id == R.id.BotonTrabajador){
            usuario.Trabajador = true
        } else if(v?.id == R.id.BotonCrearCuenta){
            conexion?.triggerDialog()
        } else if(v?.id == R.id.BotonIniciarSesion){
            if(NombreUsuario.text.isBlank() || Contrasenna.text.isBlank()){
                Toast.makeText(requireContext(),
                    getString(R.string.el_nombre_de_usuario_o_la_contrase_a_est_n_en_blanco), Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is conexion)
            conexion = context
    }
    override fun onDetach() {
        super.onDetach()
        conexion = null
    }
}