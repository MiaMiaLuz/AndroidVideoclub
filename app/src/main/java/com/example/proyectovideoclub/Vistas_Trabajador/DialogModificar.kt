package com.example.proyectovideoclub.Vistas_Trabajador

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.proyectovideoclub.Callbacks.AccionCallback
import com.example.proyectovideoclub.Callbacks.DirectoresCallback
import com.example.proyectovideoclub.Clases.Director
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R


class DialogModificar : DialogFragment, DialogInterface.OnClickListener, View.OnClickListener{

    private lateinit var layoutPelis : LinearLayout
    private lateinit var spinner: Spinner
    var ls = PeliculaController()

    lateinit var tituloEditText: EditText
    lateinit var anoEditText: EditText
    lateinit var elegirPortadaButton: Button
    lateinit var horas: NumberPicker
    lateinit var minutos: NumberPicker

    var URI : Uri? = null
    var directores = ArrayList<Director>()
    lateinit var directorSpinner: Spinner

    private var pelicula : Pelicula
    constructor(pelicula: Pelicula){
        this.pelicula = pelicula
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder = AlertDialog.Builder(requireContext())
        var dialog = AlertDialog.Builder(context)
        var inflater = requireActivity().layoutInflater
        var view : View = inflater.inflate(R.layout.dialogmodificar, null)
        spinner = view.findViewById(R.id.Selector)
        horas = view.findViewById(R.id.horas)
        horas.maxValue=10
        horas.minValue = 0
        minutos = view.findViewById(R.id.minuto)
        minutos.maxValue=59
        minutos.minValue = 0
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayListOf("Pelicula", "Director"))
        layoutPelis = view.findViewById(R.id.Pelimod)
        tituloEditText = view.findViewById(R.id.Titulo)
        anoEditText = view.findViewById(R.id.Año)
        elegirPortadaButton = view.findViewById(R.id.Elegir_portada)
        directorSpinner = view.findViewById(R.id.Director)

        anoEditText.setText(pelicula.year.toString())
        tituloEditText.setText(pelicula.titulo)

        if(pelicula.duracion != null && pelicula.duracion != ""){
            val horayminuto = pelicula.duracion.split(":")
            horas.value = Integer.parseInt(horayminuto[0])
            minutos.value = Integer.parseInt(horayminuto[1])
        }



        //Recoger los directores para visualizarlos y asignarlos a la pelicula nueva
        var local = ArrayList<Director>()
        ls.getDirectores(object : DirectoresCallback {
            //CUIDADO REVISAR BIEN EN LAS PRUEBAS PORQUE COMO ME FALLE EN LA PUTA PRESENTACION DE PROYECTO ME MATO
            override fun onDirectoresReceived(directoresb: java.util.ArrayList<Director>?) {
                if (directoresb != null && directoresb?.size != 0) {
                    //Añadir los directores al Spinner
                    directores.addAll(directoresb)
                    directorSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, directores)
                }
            }
            override fun onFailure(errorMessage: String?) {

            }
        })
        elegirPortadaButton.setOnClickListener(this)

        dialog.setView(view)

        dialog.setPositiveButton("Añadir", this)
        dialog.setNegativeButton("Cancelar", this)

        return dialog.create()
    }
    override fun onClick(dialog: DialogInterface?, which: Int) {
        when(which){
            -1->{
                if(spinner.selectedItem == "Pelicula"){
                    pelicula.year = Integer.parseInt(anoEditText.text.toString())
                    pelicula.titulo = tituloEditText.text.toString()
                    pelicula.duracion = "${horas.value}:${minutos.value}"
                    pelicula.year = Integer.parseInt(anoEditText.text.toString())

                if(URI != null){
                        pelicula.portada = URI.toString()
                    }
                    ls.createPelicula(pelicula, object : AccionCallback {
                        override fun onActionCompleted() {
                        }

                        override fun onFailure(errorMessage: String?) {
                        }
                    })
                } else {

                }
            }
            -2 -> {
                Toast.makeText(requireContext(), "No se ha insertado ningún elemento", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.Elegir_portada){
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)

        }
    }
    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == Activity.RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                URI = imgUri
            }
        }
}