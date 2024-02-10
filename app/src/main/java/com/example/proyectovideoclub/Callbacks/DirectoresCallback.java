package com.example.proyectovideoclub.Callbacks;

import com.example.proyectovideoclub.Clases.Director;
import com.example.proyectovideoclub.Clases.Pelicula;

import java.util.ArrayList;

public interface DirectoresCallback {
    void onDirectoresReceived(ArrayList<Director> directores);
    void onFailure(String errorMessage);

}


