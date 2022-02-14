package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity (tableName = "peliculas")
data class Pelicula(
    var id: String?,
    @SerializedName("title") var titulo: String,
    @SerializedName("releaseYear")var anno: String?,
    @SerializedName("runtimeMinutes") var duracion: String?,
    @SerializedName("country")var pais: String?,
    @SerializedName("directorFullname") var director: String?,
    @SerializedName("screenwriters") var guion: String?,
    @SerializedName("musicDirector")var musica: String?,
    @SerializedName("photographyDirector")var fotografia: String?,
    @SerializedName("genre")var genero: String?,
    @SerializedName("description")var sinopsis: String?,
    @SerializedName("rating") var nota: String?,
    @SerializedName("imageUrl")var imagen: String?,
    @SerializedName("trailerUrl")var trailer : String?
) : Serializable {
    @PrimaryKey
    @ColumnInfo (name="idroom")
    @Transient var idroom: String = ""
}

