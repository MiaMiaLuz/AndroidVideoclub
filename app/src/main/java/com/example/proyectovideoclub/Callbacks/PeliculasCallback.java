package com.example.proyectovideoclub.Callbacks;

import com.example.proyectovideoclub.Clases.Pelicula;
import com.example.proyectovideoclub.Clases.Usuario;

import java.util.ArrayList;

public interface PeliculasCallback {
    void onPeliculasReceived(ArrayList<Pelicula> peliculas);
    void onFailure(String errorMessage);

}


