package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.utils

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.MainActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.PeliculasActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences


class ValidacionesUtils {

    companion object {
        lateinit var preferences: Preferences
    }

    fun reiniciarApp (con: Context) {
        PeliculasActivity.preferences.guardarToken("")
        Toast.makeText(con, R.string.toast_token_caducado, Toast.LENGTH_SHORT).show()
        val intent = Intent(con, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        con.startActivity(intent)
    }

}