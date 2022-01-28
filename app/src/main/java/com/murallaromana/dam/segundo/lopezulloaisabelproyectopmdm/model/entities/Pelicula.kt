package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pelicula(
    @SerializedName("id") var id: Int,
    @SerializedName("title") var titulo: String,
    @SerializedName("releaseYear")var anno: String,
    @SerializedName("runtimeMinutes") var duracion: String,
    @SerializedName("country")var pais: String,
    @SerializedName("directorFirstname") var director: String,
    var guion: String?,
    @SerializedName("musicDirector")var musica: String,
    @SerializedName("photographyDirector")var fotografia: String,
    var reparto: String?,
    @SerializedName("genre")var genero: String,
    @SerializedName("description")var sinopsis: String,
    @SerializedName("rating") var nota: String,
    @SerializedName("imageUrl")var imagen: String,
    @SerializedName("trailerUrl")var trailer : String
) : Serializable