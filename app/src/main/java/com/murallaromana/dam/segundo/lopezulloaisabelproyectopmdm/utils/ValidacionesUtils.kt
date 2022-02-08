package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.utils

import android.content.Context
import android.content.Intent
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.MainActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences


class ValidacionesUtils {

    companion object {
        lateinit var preferences: Preferences
    }

    fun reiniciarApp (con: Context) {
        preferences.guardarToken("")
        val intent = Intent(con, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        con.startActivity(intent)
    }

}