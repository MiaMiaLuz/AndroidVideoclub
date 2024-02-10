package com.example.proyectovideoclub.Vistas_Trabajador

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
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
import android.widget.Spinner
import android.widget.TimePicker
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.DialogFragment
import com.example.proyectovideoclub.Callbacks.DirectoresCallback
import com.example.proyectovideoclub.Callbacks.PeliculasCallback
import com.example.proyectovideoclub.Clases.Director
import com.example.proyectovideoclub.Clases.Pelicula
import com.example.proyectovideoclub.DataBase.PeliculaController
import com.example.proyectovideoclub.R
import java.net.URI
import java.util.Calendar


class DialogAddPeliculas: DialogFragment(), DialogInterface.OnClickListener, OnItemSelectedListener, View.OnClickListener {

    private lateinit var layoutPelis : LinearLayout
    private lateinit var layoutAutores : LinearLayout
    private lateinit var spinner: Spinner
    var ls = PeliculaController()

    lateinit var tituloEditText: EditText
    lateinit var duracionButton: Button
    lateinit var calendar : Calendar
    lateinit var anoEditText: EditText
    lateinit var elegirPortadaButton: Button
    var URI : Uri? = null
    var idDirector = ArrayList<Int>()
    lateinit var directorSpinner: Spinner

    lateinit var nombreDirectorEditText: EditText
    lateinit var seleccionarFechaNacimientoButton: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dialog = AlertDialog.Builder(context)
        var inflater = requireActivity().layoutInflater
        var view : View = inflater.inflate(R.layout.dialogaddpeliculas, null)
        spinner = view.findViewById<Spinner>(R.id.Selector)
        spinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, arrayListOf("Pelicula", "Director"))
        layoutPelis = view.findViewById<LinearLayout>(R.id.Peliadd)
        layoutAutores = view.findViewById<LinearLayout>(R.id.addDirector)

        tituloEditText = view.findViewById(R.id.Titulo)
        duracionButton = view.findViewById(R.id.Duracion_dialog)
        anoEditText = view.findViewById(R.id.Año)
        elegirPortadaButton = view.findViewById(R.id.Elegir_portada)
        directorSpinner = view.findViewById(R.id.Director)

        nombreDirectorEditText = view.findViewById(R.id.NombreDirectorA)
        seleccionarFechaNacimientoButton = view.findViewById(R.id.AñoNac)

        spinner.onItemSelectedListener = this

        //Recoger los directores para visualizarlos y asignarlos a la pelicula nueva
        var local = ArrayList<Director>()
        ls.getDirectores(object : DirectoresCallback {
            override fun onDirectoresReceived(directores: java.util.ArrayList<Director>?) {
                if (directores != null && directores.size != 0) {
                    if(directores.size != 0) {
                        //Añadir los directores al Spinner

                        directorSpinner.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, directores)
                    }
                    Toast.makeText(requireContext(), "${local.size}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(errorMessage: String?) {

            }

        })


        duracionButton.setOnClickListener(this)
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
                    calendar.toString(),
                        Integer.parseInt(anoEditText.text.toString()),
                    URI.toString(),
                        idDirector[directorSpinner.selectedItemPosition],
                        true,
                    directorSpinner.selectedItem.toString()
                    )
                    ls.createPelicula(peli)
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
        if(v?.id == R.id.Duracion_dialog) {
            calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                requireContext(),
                { view: TimePicker, hourOfDay: Int, minute: Int ->
                    val horaSeleccionada = String.format("%02d:%02d", hourOfDay, minute)
                    Toast.makeText(
                        requireContext(),
                        "Hora seleccionada: $horaSeleccionada",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false

            )
            timePickerDialog.show()
        } else if(v?.id == R.id.Elegir_portada){
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