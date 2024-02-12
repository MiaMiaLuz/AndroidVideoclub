package com.example.proyectovideoclub.Vistas_Usuario

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.R

class AdaptadorChecker : ArrayAdapter<Pelicula> {
    private lateinit var peliculas : List<Pelicula>
    constructor(context: Context, peliculas:List<Pelicula>): super(context, R.layout.adaptadorpelis, peliculas){
        this.peliculas = peliculas
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = LayoutInflater.from(context)
        var convertView = inflater.inflate(R.layout.adaptadorpelis, null)

        var titulo = convertView.findViewById<TextView>(R.id.TituloUsuario)
        var director = convertView.findViewById<TextView>(R.id.DirectorUsuario)
        var duracion = convertView.findViewById<TextView>(R.id.DuracionUsuario)
        var anho = convertView.findViewById<TextView>(R.id.AnhoUsuario)
        var check = convertView.findViewById<CheckBox>(R.id.checkUsuario)

        titulo.setText(peliculas[position].titulo)
        director.setText(peliculas[position].nombreDirector)
        duracion.setText(peliculas[position].duracion)
        anho.setText(peliculas[position].year.toString())
        check.isChecked = peliculas[position].check

        return convertView
    }
 }