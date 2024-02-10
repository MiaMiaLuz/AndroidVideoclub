package com.example.proyectovideoclub.InicioSesion

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.proyectovideoclub.Callbacks.UsuarioCallback
import com.example.proyectovideoclub.Callbacks.UsuariosCallback
import com.example.proyectovideoclub.Clases.Encriptador
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R

class DialogCrearCuenta : DialogFragment, DialogInterface.OnClickListener, TextWatcher{

    //variables que vamos a necesitar
    var usuario = Usuario()
    private lateinit var dialog : AlertDialog
    private lateinit var DNI : EditText
    private lateinit var Nombre : EditText
    private lateinit var NombreU : EditText
    private lateinit var Pass : EditText
    var finish : Boolean = false
    var conexion : conexion? = null

    constructor(finish : Boolean, usuario: Usuario){
        this.finish = finish
        this.usuario = usuario
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(context)
        val inflater = requireActivity().layoutInflater
        val view : View = inflater.inflate(R.layout.dialoglayout1, null)
        builder.setView(view)

        DNI = view.findViewById(R.id.DialogDNI)
        Nombre = view.findViewById(R.id.DialogNombre)
        NombreU = view.findViewById(R.id.DialogNombreUsu)
        Pass = view.findViewById(R.id.DialogPass)

        DNI.addTextChangedListener(this)
        Nombre.addTextChangedListener(this)
        NombreU.addTextChangedListener(this)
        Pass.addTextChangedListener(this)

        builder.setTitle("Crear una cuenta")
        builder.setPositiveButton("Aceptar", this)
        builder.setNegativeButton("Cancelar", this)

        dialog = builder.create()
        return dialog
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        var encriptador = Encriptador()
        when(which){
            -1 ->{
                var crearUser = true
                usuario.DNI = DNI.text.toString()
                usuario.Nombre = Nombre.text.toString()
                usuario.Login = NombreU.text.toString()
                usuario.Pass = encriptador.encriptar(Pass.text.toString())

                val lc = PeliculaController()
                lc.getUsuario(usuario.Login, object : UsuarioCallback{
                    override fun onUsuarioReceived(usuarioL: Usuario?) {
                        if(usuarioL?.Login != "") {
                            Toast.makeText(
                                requireContext(),
                                "No se puede crear porque el usuario ya existe",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            lc.createUsuario(usuario)
                            Toast.makeText(
                                requireContext(),
                                "Usuario creado correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    override fun onFailure(errorMessage: String?) {
                    }
                })

            }
            -2 ->{
                Toast.makeText(requireContext(),
                    getString(R.string.has_elegido_no_crear_un_usuario), Toast.LENGTH_LONG).show()
                conexion?.repetirValoresInicioSession(false, usuario)
            }
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is conexion){
            conexion = context
        }
    }
    override fun onDetach() {
        super.onDetach()
        conexion = null
    }
    //Vuelve el botón aceptar no disponible
    override fun onStart() {
        super.onStart()
        dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = false
    }

    //Evita que se introduzca usuario sin datos
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        dialog.getButton(Dialog.BUTTON_POSITIVE).isEnabled = DNI.text.isNotBlank() && Nombre.text.isNotBlank() && NombreU.text.isNotBlank() && Pass.text.isNotBlank()
    }
    override fun afterTextChanged(s: Editable?) {

    }
    //Añadir comprobacion DNI si da tiempo
}