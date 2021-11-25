package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities

import java.io.Serializable

data class Pelicula(
    var id: Int,
    var titulo: String,
    var anno: String,
    var duracion: String,
    var pais: String,
    var director: String,
    var guion: String,
    var musica: String,
    var fotografia: String,
    var reparto: String,
    var genero: String,
    var sinopsis: String,
    var nota: String,
    var imagen: String,
    var trailer : String
) : Serializable