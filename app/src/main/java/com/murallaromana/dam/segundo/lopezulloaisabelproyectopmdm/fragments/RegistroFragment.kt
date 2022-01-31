package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.fragments

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient.apiRetrofit
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.FragmentRegistroBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class RegistroFragment : Fragment() {

    private lateinit var binding: FragmentRegistroBinding
    private lateinit var con: Context
    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegistroBinding.inflate(inflater,container, false)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        con = requireContext().applicationContext

        activity?.title = "Crear Cuenta"

        preferences = Preferences(con)

        binding.btCrearCuenta.setOnClickListener {

            var usuario = binding.tietUsuario.text.toString().trim()
            var email = binding.tietEmail.text.toString().trim()
            var telefono = binding.tietTelefono.text.toString().trim()
            var contrasenhaRegistro = binding.tietContrasenaRegistro.text.toString().trim()
            var contrasenhaValidar = binding.tietValidarContrasena.text.toString().trim()

            if (TextUtils.isEmpty(usuario) ||
                TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(telefono) ||
                TextUtils.isEmpty(contrasenhaRegistro) ||
                TextUtils.isEmpty(contrasenhaValidar)
            ) {
                Toast.makeText(activity, R.string.toast_campos_vacios, Toast.LENGTH_SHORT).show()
            }
            else if (!validarEmail(email)){
                Toast.makeText(activity, R.string.toast_email_no_valido, Toast.LENGTH_SHORT).show()
            }
            else if (contrasenhaRegistro.equals(contrasenhaValidar)) {
                    /*val sharedPref = activity?.getSharedPreferences(
                        "datos",
                        Context.MODE_PRIVATE
                    )*/
                    /*val editor = sharedPref?.edit()
                    editor?.putString("email", binding.tietEmail.text.toString())
                    editor?.putString("contrasena", binding.tietContrasenaRegistro.text.toString())
                    editor?.apply()*/

                    val u = Usuario(null, email, contrasenhaRegistro)

                    val signupCall = apiRetrofit.registrarse(u)

                    signupCall.enqueue(object: Callback<Unit> {
                        override fun onFailure(call: Call<Unit>, t: Throwable) {
                            Toast.makeText(activity, R.string.toast_no_registrado, Toast.LENGTH_SHORT).show()
                            Log.d("respuesta: onFailure", t.toString())

                        }

                        override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                            Log.d("respuesta: onResponse", response.toString())

                            if (response.code() > 299 || response.code() < 200) {
                                Toast.makeText(activity, R.string.toast_no_registrado, Toast.LENGTH_SHORT).show()
                            } else {
                                preferences.guardarEmail(email)
                                activity?.onBackPressed()
                            }

                        }
                    })


                } else {
                    Toast.makeText(activity, R.string.toast_contrasenas_no_coinciden, Toast.LENGTH_SHORT)
                        .show()
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