package com.example.proyectovideoclub.Vistas

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.R

class AdaptadorR(val peliculas : ArrayList<Pelicula>): RecyclerView.Adapter<AdaptadorR.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorR.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layoutrecycler, parent, false)
        val context = parent.context
        return ViewHolder(v, context)
    }

    override fun onBindViewHolder(holder: AdaptadorR.ViewHolder, position: Int) {
        val peli = peliculas[position]
        if(peli.disponible){
            holder.layout.setBackgroundColor(ContextCompat.getColor( holder.context , R.color.BabyBlue))
        } else {
            holder.layout.setBackgroundColor(ContextCompat.getColor( holder.context , R.color.pink))
        }
        holder.foto.setImageResource(peli.imagen)
        holder.titulo.text = peli.titulo
        holder.duracion.text = peli.duracion.toString()
        holder.año.text = peli.year.toString()
        //HACER RECUPERACION DE BBDD
        holder.director.text = peli.ID_director.toString()
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    class ViewHolder(v: View, context: Context):RecyclerView.ViewHolder(v){
        var layout : CardView
        var foto : ImageView
        var titulo : TextView
        var duracion : TextView
        var año : TextView
        var director : TextView
        var context = context
        init {
            layout = v.findViewById(R.id.disponible)
            foto = v.findViewById(R.id.Foto)
            titulo = v.findViewById(R.id.Titulo)
            duracion = v.findViewById(R.id.Duracion)
            año = v.findViewById(R.id.Año)
            director = v.findViewById(R.id.Director)
        }
    }
}