package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

@Dao
interface PeliculaDao {
    @Query("SELECT * from peliculas")
    fun findAll(): List<Pelicula>

    @Query("Delete from peliculas")
    fun deleteAll()

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(peliculas: List<Pelicula>?)
}
