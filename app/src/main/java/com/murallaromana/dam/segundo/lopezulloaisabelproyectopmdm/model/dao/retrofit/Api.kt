package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.retrofit

import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Token
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Api {

    @POST("users/signup")
    fun registrarse(@Body usuario: Usuario): Call<Unit>

    @POST("users/login")
    fun iniciar(@Body usuario: Usuario): Call<Token>

    @GET("movies")
    fun getPeliculas(): Call<List<Pelicula>>

    // TODO: declarar todos los métodos del API siguiendo la documentación.



}