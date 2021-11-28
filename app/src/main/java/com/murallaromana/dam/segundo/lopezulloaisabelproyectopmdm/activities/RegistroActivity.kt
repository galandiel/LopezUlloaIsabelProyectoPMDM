package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityRegistroBinding
import java.util.regex.Pattern

class RegistroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCrearCuenta.setOnClickListener {
            if (TextUtils.isEmpty(binding.tietUsuario.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietEmail.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietTelefono.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietContrasenaRegistro.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietValidarContrasena.text.toString().trim())
            ) {
                Toast.makeText(this, R.string.toast_campos_vacios, Toast.LENGTH_SHORT).show()
            } else if (!validarEmail(binding.tietEmail.text.toString().trim())){
                Toast.makeText(this, R.string.toast_email_no_valido, Toast.LENGTH_SHORT).show()
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
                    onBackPressed()
                } else {
                    Toast.makeText(this, R.string.toast_contrasenas_no_coinciden, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun validarEmail(email:String):Boolean{
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}