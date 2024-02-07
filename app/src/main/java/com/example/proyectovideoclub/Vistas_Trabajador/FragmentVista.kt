package com.example.proyectovideoclub.Vistas_Trabajador

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
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.Clases.Usuario
import com.example.proyectovideoclub.R

class FragmentVista : Fragment(), TextWatcher{
    private lateinit var filtro : EditText
    private lateinit var recycler : RecyclerView
    private lateinit var peliculas : ArrayList<Pelicula>
    private lateinit var usuario : Usuario
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            usuario = arguments?.getSerializable("usuario") as Usuario
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        var view : View = inflater.inflate(R.layout.fragment_vista, container, false)
        recycler = view.findViewById(R.id.view)
        filtro = view.findViewById(R.id.filtro)
        actualizar(peliculas)
        registerForContextMenu(recycler)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menuprincipal, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
        if(item.itemId == R.id.menufiltrar){
            //Filtrar aqui
            var alquiladas : ArrayList<Pelicula>
            actualizar(alquiladas)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        activity?.menuInflater?.inflate(R.menu.menucontextual, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        if(item.itemId == R.id.devolver){

        } else if (item.itemId == R.id.modificar){

        } else if (item.itemId == R.id.extender){

        }  else if (item.itemId == R.id.eliminar){

        }
    }

    //para actualizar el recycler cuando se hacen cambios
    fun actualizar(pelicula: ArrayList<Pelicula>){
        recycler.adapter = AdaptadorR(peliculas)
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    //Cambios en el filtro
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }
    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        //Filtroooo
        var filtrada : ArrayList<Pelicula>
        //actualizar(filtrada)
    }
    override fun afterTextChanged(s: Editable?) {

    }
}