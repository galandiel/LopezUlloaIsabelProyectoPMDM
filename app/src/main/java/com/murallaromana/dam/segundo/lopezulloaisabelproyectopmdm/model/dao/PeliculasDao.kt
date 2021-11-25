package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao

import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

interface PeliculasDao {
    fun getAll(): List<Pelicula>
}