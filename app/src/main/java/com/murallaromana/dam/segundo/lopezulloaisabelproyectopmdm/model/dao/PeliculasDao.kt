package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao

import android.os.Bundle
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

interface PeliculasDao {

    fun getAll(): List<Pelicula>
}