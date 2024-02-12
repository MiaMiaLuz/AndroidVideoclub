package com.example.proyectovideoclub.Clases

class Filtros {
    var fTitulo : String = ""
    var fDirector: String = ""
    var fAnho : Int = 0
    private var lista : ArrayList<Pelicula>

    constructor(lista : ArrayList<Pelicula>){
        this.lista = lista
    }

    fun filtrar() : ArrayList<Pelicula>{
        var listaFiltrada = ArrayList<Pelicula>()
        listaFiltrada = filtrarTitulo(lista)
        listaFiltrada = filtrarDirector(listaFiltrada)
        listaFiltrada = filtrarAnho(listaFiltrada)
        return listaFiltrada
    }
    private fun filtrarTitulo(lista : ArrayList<Pelicula>):ArrayList<Pelicula>{
        var listaFiltrada = ArrayList<Pelicula>()
        if(fTitulo != ""){
             for(titulo in lista){
                 if(titulo.titulo.contains(fTitulo))
                     listaFiltrada.add(titulo)
             }
            return listaFiltrada
         }
        return lista
     }
    private fun filtrarDirector(lista : ArrayList<Pelicula>):ArrayList<Pelicula>{
        var listaFiltrada = ArrayList<Pelicula>()
        if(fDirector != ""){
            for(director in lista){
                if(director.nombreDirector.contains(fDirector))
                    listaFiltrada.add(director)
            }
            return listaFiltrada
        }
        return lista
     }
    private fun filtrarAnho(lista : ArrayList<Pelicula>): ArrayList<Pelicula>{
        var listaFiltrada = ArrayList<Pelicula>()
        if(fAnho != 0){
            var anhoString = fAnho.toString()
            for(anho in lista){
                if(anho.year.toString().contains(anhoString))
                    listaFiltrada.add(anho)
            }
            return listaFiltrada
        }
        return lista
     }
}