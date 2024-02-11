package com.example.proyectovideoclub.Callbacks;

import com.example.proyectovideoclub.Clases.Alquiler;
import com.example.proyectovideoclub.Clases.Director;

import java.util.ArrayList;

public interface AlquileresCallback {
    void onAlquileresReceived(ArrayList<Alquiler> Alquileres);
    void onFailure(String errorMessage);

}


