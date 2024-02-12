package com.example.proyectovideoclub.Vistas_Trabajador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Callbacks.AccionCallback
import com.example.proyectovideoclub.Callbacks.PeliculasCallback
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R


class AdaptadorR(): RecyclerView.Adapter<AdaptadorR.ViewHolder>(){

    lateinit var contexto : Context
    lateinit var peliculas: ArrayList<Pelicula>
    constructor(contexto: Context, peliculas : ArrayList<Pelicula>) : this() {
        this.contexto = contexto
        this.peliculas = peliculas
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorR.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.layoutrecycler, parent, false)
        val context = contexto

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
        holder.duracion.text = peli.duracion
        holder.año.text = peli.year.toString()
        holder.director.text = peli.nombreDirector
        holder.button.setOnClickListener{
            val popUp = PopupMenu(contexto, holder.button)
            popUp.inflate(R.menu.menucontextual)
            popUp.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.modificar-> {
                        var dialog = DialogModificar(peli)
                        dialog.show((contexto as FragmentActivity).supportFragmentManager, "Add")
                        getPeliculas("")
                        true
                    }
                    R.id.eliminar-> {
                        var ls = PeliculaController()
                        ls.borrarPelicula(peli.ID)
                        getPeliculas("")
                        true
                    }
                    R.id.devolver->{
                        peli.disponible = true
                        var ls = PeliculaController()
                        ls.createPelicula(peli,object : AccionCallback {
                            override fun onActionCompleted() {
                                getPeliculas("")
                            }

                            override fun onFailure(errorMessage: String?) {
                            }
                        })
                        true
                    }

                    else -> false
                }
            }
            popUp.show()
        }

    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    class ViewHolder(v: View, context: Context):RecyclerView.ViewHolder(v){
        var layout : LinearLayout
        var foto : ImageView
        var titulo : TextView
        var duracion : TextView
        var año : TextView
        var director : TextView
        var button : TextView
        var context = context
        init {
            layout = v.findViewById(R.id.disponible)
            foto = v.findViewById(R.id.Foto)
            titulo = v.findViewById(R.id.Titulo)
            duracion = v.findViewById(R.id.Duracion)
            año = v.findViewById(R.id.Año)
            director = v.findViewById(R.id.Director)
            button = v.findViewById(R.id.textViewOptions)
        }
    }
    private fun getPeliculas(dni : String){
        var ls = PeliculaController()
        peliculas.clear()
        ls.getPeliculas( dni ,object : PeliculasCallback {
            override fun onPeliculasReceived(pelis: java.util.ArrayList<Pelicula>?) {
                if (pelis != null) {
                    peliculas.addAll(pelis)
                    notifyDataSetChanged()
                }
            }
            override fun onFailure(errorMessage: String?) {
            }
        })
    }
}