package com.example.proyectovideoclub.Vistas_Trabajador

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnCreateContextMenuListener
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Callbacks.AlquileresCallback
import com.example.proyectovideoclub.Callbacks.PeliculasCallback
import com.example.proyectovideoclub.Clases.Alquiler
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.Clases.conexion
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentVista : Fragment, TextWatcher, View.OnClickListener, OnCreateContextMenuListener{
    private var contexto : Context
    private lateinit var filtro : EditText
    private lateinit var recycler : RecyclerView
    private lateinit var boton : FloatingActionButton
    private var local = ArrayList<Pelicula>()
    private lateinit var usuario : Usuario
    private var conexion : conexion? = null
    constructor(context: Context){
        this.contexto = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Recibimos en los argumentos el usuario activo
        arguments?.let {
            usuario = arguments?.getSerializable("usuario") as Usuario
        }
        recycler = RecyclerView(contexto)
        setHasOptionsMenu(true)
        registerForContextMenu(recycler)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_vista, container, false)
        recycler = view.findViewById(R.id.view)
        filtro = view.findViewById(R.id.filtro)
        boton = view.findViewById(R.id.botonADD)

        getPeliculas("")
        registerForContextMenu(recycler)
        filtro.addTextChangedListener(this)
        boton.setOnClickListener(this)

        return view
    }

    //para actualizar el recycler cuando se hacen cambios
    fun actualizar(pelicula: ArrayList<Pelicula>): ArrayList<Pelicula>{
        recycler.adapter = AdaptadorR(requireContext(), pelicula)
        recycler.layoutManager = LinearLayoutManager(contexto)
        return pelicula
    }

    //Cambios en el filtro
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    //ARREGLAR
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        getPeliculas(filtro.text.toString())
    }
    override fun afterTextChanged(s: Editable?) {

    }
    //Recibimos las peliculas implementando la interfaz para obtenerlas
    private fun getPeliculas(dni : String){
        var ls = PeliculaController()
        local.clear()
        ls.getPeliculas( dni ,object : PeliculasCallback{
            override fun onPeliculasReceived(peliculas: java.util.ArrayList<Pelicula>?) {
                if (peliculas != null) {
                    local.addAll(peliculas)
                    actualizar(local)
                }
            }
            override fun onFailure(errorMessage: String?) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menufiltrar){
            var filtradas = ArrayList<Pelicula>()
            for(filtrada in local) {
                if(!filtrada.disponible)
                    filtradas.add(filtrada)
            }
            actualizar(filtradas)
        } else if(item.itemId == R.id.menutodas){
            actualizar(local)
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onClick(v: View?) {
        conexion?.triggeradd()
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