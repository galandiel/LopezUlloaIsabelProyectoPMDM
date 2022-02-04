package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.retrofit

import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Token
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Usuario
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @POST("users/signup")
    fun registrarse(@Body usuario: Usuario): Call<Unit>

    @POST("users/login")
    fun iniciar(@Body usuario: Usuario): Call<Token>

    @GET("movies")
    fun getPeliculas(@Header("Authorization") token: String): Call<List<Pelicula>>

    @GET("movies/{id}")
    fun getById(@Header("Authorization") token: String,
                @Path("id") id: String?): Call<Pelicula>

    @POST("movies")
    fun anadir(@Header("Authorization") token: String,
               @Body pelicula: Pelicula): Call<Unit>

    @PUT("movies")
    fun editar(@Header("Authorization") token: String,
               @Body pelicula: Pelicula): Call<Unit>

    @DELETE("movies/{id}")
    fun eliminar(@Header("Authorization") token: String,
                 @Path("id") id: String?): Call<Unit>

}