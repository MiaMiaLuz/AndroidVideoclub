package com.example.proyectovideoclub.Vistas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.R

class FragmentVista : Fragment() {
    private lateinit var recycler : RecyclerView
    private lateinit var peliculas : ArrayList<Pelicula>
    private lateinit var usuario : Usuario
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
        var view : View = inflater.inflate(R.layout.fragment_vista, container, false)
        recycler = view.findViewById(R.id.view)
        recycler.adapter = AdaptadorR(peliculas)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        return view
    }

}