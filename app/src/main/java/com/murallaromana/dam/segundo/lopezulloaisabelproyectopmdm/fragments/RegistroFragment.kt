package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityRegistroBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.FragmentInicioBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.FragmentRegistroBinding
import java.util.regex.Pattern

class RegistroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistroBinding.inflate(inflater,container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        activity?.title = "Crear Cuenta"

        binding.btCrearCuenta.setOnClickListener {
            if (TextUtils.isEmpty(binding.tietUsuario.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietEmail.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietTelefono.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietContrasenaRegistro.text.toString().trim()) ||
                TextUtils.isEmpty(binding.tietValidarContrasena.text.toString().trim())
            ) {
                Toast.makeText(activity, R.string.toast_campos_vacios, Toast.LENGTH_SHORT).show()
            } else if (!validarEmail(binding.tietEmail.text.toString().trim())){
                Toast.makeText(activity, R.string.toast_email_no_valido, Toast.LENGTH_SHORT).show()
            } else {
                if (TextUtils.equals(
                        binding.tietContrasenaRegistro.text.toString(),
                        binding.tietValidarContrasena.text.toString()
                    )
                ) {
                    val sharedPref = activity?.getSharedPreferences(
                        "datos",
                        Context.MODE_PRIVATE
                    )
                    val editor = sharedPref?.edit()
                    editor?.putString("email", binding.tietEmail.text.toString())
                    editor?.putString("contrasena", binding.tietContrasenaRegistro.text.toString())
                    editor?.apply()

                    val ft = activity?.supportFragmentManager?.beginTransaction()
                    ft?.replace(R.id.contenedorFragments, InicioFragment())
                    ft?.commit()

                    activity?.onBackPressed()
                } else {
                    Toast.makeText(activity, R.string.toast_contrasenas_no_coinciden, Toast.LENGTH_SHORT)
                        .show()
                }
            }
/*PRE-CIO-SO*/
            /*PRE-CI-O-SO*/
            /*Non sei se existe diptongo/hiato*/
        }

        return binding.root
    }

    private fun validarEmail(email:String):Boolean{
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }
}