package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.utils

import android.content.Context
import android.content.Intent
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.MainActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build


class ValidacionesUtils {

    companion object {
        lateinit var preferences: Preferences
    }

    fun reiniciarApp (con: Context) {
        preferences = Preferences(con)
        preferences.guardarToken("")
        val intent = Intent(con, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        con.startActivity(intent)
    }

    fun hayConexion(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw      = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

}