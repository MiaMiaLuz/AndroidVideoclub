package com.example.proyectovideoclub.Vistas_Trabajador

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
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
import java.io.ByteArrayOutputStream
import java.io.InputStream


class DialogAddPeliculas: DialogFragment(), DialogInterface.OnClickListener, OnItemSelectedListener, View.OnClickListener
{

    private lateinit var layoutPelis : LinearLayout
    private lateinit var layoutAutores : LinearLayout
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
    lateinit var horaSeleccionada : String
    lateinit var nombreDirectorEditText: EditText
    lateinit var seleccionarFechaNacimientoButton: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = AlertDialog.Builder(context)
        var inflater = requireActivity().layoutInflater
        var view : View = inflater.inflate(R.layout.dialogaddpeliculas, null)
        spinner = view.findViewById(R.id.Selector)
        horas = view.findViewById(R.id.horas)
        horas.maxValue=10
        horas.minValue = 0
        minutos = view.findViewById(R.id.minuto)
        minutos.maxValue=59
        minutos.minValue = 0
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayListOf("Pelicula", "Director"))
        layoutPelis = view.findViewById(R.id.Peliadd)
        layoutAutores = view.findViewById(R.id.addDirector)

        tituloEditText = view.findViewById(R.id.Titulo)
        anoEditText = view.findViewById(R.id.Año)


        elegirPortadaButton = view.findViewById(R.id.Elegir_portada)
        directorSpinner = view.findViewById(R.id.Director)

        nombreDirectorEditText = view.findViewById(R.id.NombreDirectorA)
        seleccionarFechaNacimientoButton = view.findViewById(R.id.AñoNac)

        spinner.onItemSelectedListener = this

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
                    var peli = Pelicula(
                        tituloEditText.text.toString(),
                        "${horas.value}:${minutos.value}",
                        Integer.parseInt(anoEditText.text.toString()),
                        directores[directorSpinner.selectedItemPosition].IDDirector,
                        true,
                        directorSpinner.selectedItem.toString()
                    )
                    if(URI != null){
                        val cv = ContentValues()
                        val inputStream =  activity?.contentResolver?.openInputStream(URI!!)
                        val drawable = Drawable.createFromStream(inputStream, URI.toString())
                        val bitmap = (drawable as BitmapDrawable).bitmap
                        val baos = ByteArrayOutputStream()
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                        val b = baos.toByteArray()
                        peli.portada = b
                    }
                    ls.createPelicula(peli, object : AccionCallback {
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

    //Ver la seleccion del Spinner para establecer la visibilidad de los layouts
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(spinner.selectedItem == "Pelicula"){
            layoutPelis.visibility = View.VISIBLE
            layoutPelis.isEnabled = true
            layoutAutores.visibility = View.GONE
            layoutAutores.isEnabled = false
        } else {
            layoutPelis.visibility = View.GONE
            layoutPelis.isEnabled = false
            layoutAutores.visibility = View.VISIBLE
            layoutAutores.isEnabled = true
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
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
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                val imgUri = data?.data
                URI = imgUri
            }
        }
}