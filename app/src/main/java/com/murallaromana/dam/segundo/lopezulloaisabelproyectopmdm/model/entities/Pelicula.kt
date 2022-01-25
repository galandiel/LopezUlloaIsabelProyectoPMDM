package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var titulo: String,
    var anno: String,
    @SerializedName("runtimeMinutes") var duracion: String,
    var pais: String,
    var director: String,
    var guion: String,
    var musica: String,
    var fotografia: String,
    var reparto: String,
    var genero: String,
    var sinopsis: String,
    @SerializedName("rating") var nota: String,
    var imagen: String,
    var trailer : String
) : Serializable