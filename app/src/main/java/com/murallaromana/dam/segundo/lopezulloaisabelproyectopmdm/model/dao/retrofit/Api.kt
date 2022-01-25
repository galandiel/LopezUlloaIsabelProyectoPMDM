package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.retrofit

import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    // TODO: declarar todos los métodos del API siguiendo la documentación.
    // método registro
    // método login (tiene que devolver un token) (tendremos que crear una clase token con un
    // private var token)
}