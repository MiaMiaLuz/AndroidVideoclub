package com.example.proyectovideoclub.Vistas_Usuario

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.proyectovideoclub.Clases.Filtros
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.R

class DialogFiltro: DialogFragment, DialogInterface.OnClickListener {

    private lateinit var titulo : EditText
    private lateinit var director : EditText
    private lateinit var anho : EditText
    private var array : ArrayList<Pelicula>
    private var tituloB : Boolean = false
    private var directorB : Boolean = false
    private var anhoB : Boolean = false
    private var conexion : conexion? = null

    constructor(array : ArrayList<Pelicula>){
        this.array = array
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        var inflater = requireActivity().layoutInflater
        var view : View = inflater.inflate(R.layout.dialogfiltro, null)

        titulo = view.findViewById(R.id.filtroTitulo)
        director = view.findViewById(R.id.filtroDirector)
        anho = view.findViewById(R.id.filtroAnho)

        titulo.isEnabled = tituloB
        director.isEnabled = directorB
        anho.isEnabled = anhoB

        builder.setView(view)
        builder.setPositiveButton("Aceptar", this)
        builder.setNegativeButton("Cancelar", this)

        return super.onCreateDialog(savedInstanceState)
    }
    override fun onClick(dialog: DialogInterface?, which: Int) {
        when(which){
            -1 ->{
                var filtros = Filtros(array)
                if(titulo.isEnabled){
                    filtros.fTitulo = titulo.text.toString()
                } else if(director.isEnabled){
                    filtros.fDirector = director.text.toString()
                } else if(anho.isEnabled){
                    filtros.fAnho = Integer.parseInt(anho.text.toString())
                }
                array = filtros.filtrar()
            }
            -2 ->{
                Toast.makeText(requireContext(),
                    getString(R.string.has_cancelado_el_filtrado), Toast.LENGTH_SHORT).show()
            }
        }
    }
}