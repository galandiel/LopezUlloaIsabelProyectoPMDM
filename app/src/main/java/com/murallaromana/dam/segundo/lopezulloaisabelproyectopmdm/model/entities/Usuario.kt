package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities

import com.google.gson.annotations.SerializedName

class Usuario (
    var id: String?,
    var email: String,
    @SerializedName("password") var contrasenha: String
)