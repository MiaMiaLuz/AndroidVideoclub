package com.example.proyectovideoclub.Vistas_Usuario

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Callbacks.PeliculasCallback
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentVistaUsuario : Fragment {

    private lateinit var checkList : ListView
    private lateinit var boton : FloatingActionButton
    private var context: Context
    private var local = ArrayList<Pelicula>()

    constructor(context: Context){
        this.context = context
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_vista_usuario, container, false)

        checkList = view.findViewById(R.id.viewUser)
        boton = view.findViewById(R.id.botonADDUser)

        var ls = PeliculaController()
        ls.getPeliculas("",object : PeliculasCallback {
            override fun onPeliculasReceived(peliculas: java.util.ArrayList<Pelicula>?) {
                if (peliculas != null) {
                    local.addAll(peliculas)
                }
                checkList.adapter = ArrayAdapter(context, android.R.layout.simple_list_item_multiple_choice, local)
            }
            override fun onFailure(errorMessage: String?) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        })

        return view
    }


}