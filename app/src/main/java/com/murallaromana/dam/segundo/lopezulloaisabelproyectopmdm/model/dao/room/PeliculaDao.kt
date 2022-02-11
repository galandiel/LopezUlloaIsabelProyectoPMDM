package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

@Dao
interface PeliculaDao {
    @Query("SELECT * from pelicula")
    fun findAll(): List<Pelicula>

    @Insert
    fun insertAll(peliculas: Pelicula)
}