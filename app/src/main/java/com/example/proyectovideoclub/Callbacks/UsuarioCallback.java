package com.example.proyectovideoclub.Callbacks;

import com.example.proyectovideoclub.Clases.Pelicula;
import com.example.proyectovideoclub.Clases.Usuario;

import java.util.ArrayList;

public interface UsuarioCallback {
    void onUsuarioReceived(Usuario usuario);
    void onFailure(String errorMessage);

}


