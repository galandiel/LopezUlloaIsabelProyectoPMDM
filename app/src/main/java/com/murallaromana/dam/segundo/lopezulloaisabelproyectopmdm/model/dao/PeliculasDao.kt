package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao

import android.os.Bundle
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

interface PeliculasDao {
    //public List<Personaje> getAll();
    fun getAll(): List<Pelicula>
}