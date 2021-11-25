package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPref = getSharedPreferences("datos", MODE_PRIVATE)
        val email = sharedPref.getString("email", "nombre@dominio.com")
        val contrasena = sharedPref.getString("contrasena", "")
        binding.tietEmail.setText(email?.trim())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        title = "FilmGoer"

        binding.btAcceder.setOnClickListener {
            val intent = Intent(this, PeliculasActivity::class.java)
            if (binding.tietEmail.text.toString() == "") {
                Toast.makeText(this, "Introduce un email", Toast.LENGTH_SHORT).show()
            } else if (binding.tietContrasena.text.toString() == "") {
                Toast.makeText(this, "Introduce una contraseña", Toast.LENGTH_SHORT).show()
            } else if (TextUtils.equals(binding.tietContrasena.text.toString().trim(), contrasena)
            ) {
                startActivity(intent)
            } else {
                Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }

    }
}