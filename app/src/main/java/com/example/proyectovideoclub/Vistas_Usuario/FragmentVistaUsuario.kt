package com.example.proyectovideoclub.Vistas_Usuario

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Callbacks.AlquileresCallback
import com.example.proyectovideoclub.Callbacks.PeliculasCallback
import com.example.proyectovideoclub.Clases.Alquiler
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentVistaUsuario : Fragment, View.OnClickListener {

    private lateinit var checkList : ListView
    private lateinit var boton : FloatingActionButton
    private lateinit var context: Context
    private var local = ArrayList<Pelicula>()
    private var titulo : Boolean = false
    private var director : Boolean = false
    private var anho : Boolean = false

    constructor(context: Context){
        this.context = context
    }
    constructor()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {

        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_vista_usuario, container, false)

        checkList = view.findViewById(R.id.viewUser)
        boton = view.findViewById(R.id.botonAlquilar)

        var ls = PeliculaController()
        ls.getPeliculas("",object : PeliculasCallback {
            override fun onPeliculasReceived(peliculas: java.util.ArrayList<Pelicula>?) {
                if (peliculas != null) {
                    local.addAll(peliculas)
                }
                refrescar()
            }
            override fun onFailure(errorMessage: String?) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }

    fun refrescar(){
        checkList.adapter = AdaptadorChecker(context, local)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.titulo){
            titulo = item.isChecked
        } else if(item.itemId == R.id.director){
            director = item.isChecked
        } else if(item.itemId == R.id.anho){
            anho = item.isChecked
        } else if (item.itemId == R.id.menufiltrar){
            lanzarDialogFiltro(titulo,director,anho)
        }
        return super.onOptionsItemSelected(item)
    }

    fun lanzarDialogFiltro(titulo : Boolean, director : Boolean , anho: Boolean){
        var dialogFiltro = DialogFiltro(local)
        dialogFiltro.show((context as FragmentActivity).supportFragmentManager, "filtro")
        refrescar()
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.botonAlquilar){
           var ls = PeliculaController()
            ls.getAlquileres(object : AlquileresCallback{
                override fun onAlquileresReceived(Alquileres: java.util.ArrayList<Alquiler>?) {

                }
                override fun onFailure(errorMessage: String?) {

                }
            })
        }
    }

}