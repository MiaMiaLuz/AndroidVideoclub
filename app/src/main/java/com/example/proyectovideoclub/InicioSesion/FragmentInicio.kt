package com.example.proyectovideoclub.InicioSesion

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.proyectovideoclub.Callbacks.UsuarioCallback
import com.example.proyectovideoclub.Clases.Encriptador
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R

class FragmentInicio : Fragment(), View.OnClickListener, TextWatcher {

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

        Contrasenna.addTextChangedListener(this)
        NombreUsuario.addTextChangedListener(this)

        if(usuario.Trabajador){
            fondo.background = ContextCompat.getDrawable(requireContext(), R.drawable.fondoiniciob)
        } else {
            fondo.background = ContextCompat.getDrawable(requireContext(), R.drawable.fondoinicio)
        }

        BotonCliente.setOnClickListener(this)
        BotonTrabajador.setOnClickListener(this)
        CrearCuenta.setOnClickListener(this)
        InicioSesion.setOnClickListener(this)

        return view
    }
    override fun onClick(v: View?) {
        //establecen si es trabajador o cliente el usuario
        if(v?.id == R.id.BotonCliente){
            usuario.Trabajador = false
            fondo.background = ContextCompat.getDrawable(requireContext(), R.drawable.fondoinicio)
        } else if(v?.id == R.id.BotonTrabajador){
            usuario.Trabajador = true
            fondo.background = ContextCompat.getDrawable(requireContext(), R.drawable.fondoiniciob)
        //Dispara el dialog para crear la cuenta
        } else if(v?.id == R.id.BotonCrearCuenta){
            println(usuario.Trabajador)
            conexion?.triggerDialog(usuario)
        //comprueba si es un usuario valido
        } else if(v?.id == R.id.BotonIniciarSesion){
            usuario.Login = NombreUsuario.text.toString()
            usuario.Pass = Contrasenna.text.toString()
            val lc = PeliculaController()
            lc.getUsuario(usuario.Login, object : UsuarioCallback {
                override fun onUsuarioReceived(usuarioCall: Usuario) {
                    var encriptador = Encriptador()
                    if(encriptador.comparar(usuario.Pass, usuarioCall.Pass) && usuario.Trabajador == usuarioCall.Trabajador){
                        Toast.makeText(requireContext(),
                            getString(R.string.contrase_a_correcta), Toast.LENGTH_SHORT).show()
                        conexion?.inicioSesion(usuarioCall)
                    } else {
                        Toast.makeText(requireContext(),
                            getString(R.string.contrase_a_incorrecta), Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(errorMessage: String) {

                }
            })

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

    override fun onStart() {
        super.onStart()
        InicioSesion.isEnabled = false
    }
    //Comprueba si los campos estan rellenos
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        InicioSesion.isEnabled = (NombreUsuario.text.isNotBlank() && Contrasenna.text.isNotBlank())
    }

    override fun afterTextChanged(s: Editable?) {

    }
}