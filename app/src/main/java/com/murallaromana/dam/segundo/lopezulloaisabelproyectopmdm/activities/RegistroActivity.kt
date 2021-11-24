package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCrearCuenta.setOnClickListener {
            val backLogin = Intent(this, MainActivity::class.java)
            if (TextUtils.equals(binding.tietUsuario.text.toString().trim(), "") ||
                TextUtils.equals(binding.tietEmail.text.toString().trim(), "") ||
                TextUtils.equals(binding.tietTelefono.text.toString().trim(), "") ||
                TextUtils.equals(binding.tietContrasenaRegistro.text.toString().trim(), "") ||
                TextUtils.equals(binding.tietValidarContrasena.text.toString().trim(), "")
            ) {
                Toast.makeText(this, "Hay campos vacíos", Toast.LENGTH_SHORT).show()
            } else {
                if (TextUtils.equals(
                        binding.tietContrasenaRegistro.text.toString(),
                        binding.tietValidarContrasena.text.toString()
                    )
                ) {
                    val sharedPref = getSharedPreferences(
                        "datos",
                        Context.MODE_PRIVATE
                    )
                    val editor = sharedPref.edit()
                    editor.putString("email", binding.tietEmail.text.toString())
                    editor.putString("contrasena", binding.tietContrasenaRegistro.text.toString())
                    editor.apply()
                    startActivity(backLogin)
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

}