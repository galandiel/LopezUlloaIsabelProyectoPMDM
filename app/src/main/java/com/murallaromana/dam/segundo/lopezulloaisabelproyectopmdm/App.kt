package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm

import android.app.Application
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.PeliculasDaoMockImpl
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

class App : Application() {
    companion object {
        var peliculas: ArrayList<Pelicula> = ArrayList()
    }

    override fun onCreate() {
        super.onCreate()

        val dao = PeliculasDaoMockImpl()

        peliculas = dao.getAll()
    }

}