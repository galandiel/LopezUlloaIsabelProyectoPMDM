package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.config.retrofitConfig.RetrofitClient.apiRetrofit
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.PeliculasActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.FragmentInicioBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Token
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InicioFragment : Fragment() {

    private lateinit var binding: FragmentInicioBinding
    private lateinit var con: Context
    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        con = requireContext().applicationContext

        activity?.title = "FilmGoer"

        preferences = Preferences(con)

        //Activar botón Acceder
        binding.btAcceder.isEnabled = true

        //Comprobar token para saltar directamente a la pantalla de películas
        if (!preferences.recuperarToken().isNullOrEmpty()) {
            val intent = Intent(activity, PeliculasActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
            binding.btAcceder.setOnClickListener {
                binding.btAcceder.isEnabled = false
                binding.pbCargando.visibility = View.VISIBLE
                val email = binding.tietEmail.text.toString().trim()
                val contrasenha = binding.tietContrasena.text.toString().trim()

                //Comprobaciones
                if (email == "") {
                    Toast.makeText(activity, R.string.toast_introduce_email, Toast.LENGTH_SHORT)
                        .show()
                    binding.btAcceder.isEnabled = true
                    binding.pbCargando.visibility = View.GONE
                } else if (contrasenha == "") {
                    binding.btAcceder.isEnabled = true
                    binding.pbCargando.visibility = View.GONE
                    Toast.makeText(
                        activity,
                        R.string.toast_introduce_contrasena,
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    val u = Usuario(null, email, contrasenha)

                    //Iniciar sesión
                    val loginCall = apiRetrofit.iniciar(u)
                    loginCall.enqueue(object : Callback<Token> {
                        override fun onFailure(call: Call<Token>, t: Throwable) {
                            Toast.makeText(activity, R.string.toast_no_iniciado, Toast.LENGTH_SHORT)
                                .show()
                            binding.btAcceder.isEnabled = true
                            binding.pbCargando.visibility = View.GONE
                        }

                        override fun onResponse(call: Call<Token>, response: Response<Token>) {
                            if (response.code() < 200 || response.code() > 299) {
                                Toast.makeText(activity, R.string.toast_no_iniciado,Toast.LENGTH_SHORT).show()
                                binding.btAcceder.isEnabled = true
                                binding.pbCargando.visibility = View.GONE
                            } else {
                                //Guardar el token en sharedPreferences
                                val token = response.body()?.token.toString()
                                preferences.guardarToken(token)

                                //Iniciar nueva activity
                                val intent = Intent(activity, PeliculasActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                startActivity(intent)
                            }
                        }
                    })
                }
            }

        binding.tvCrearCuenta.setOnClickListener {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            ft?.addToBackStack(null)
            ft?.replace(R.id.contenedorFragments, RegistroFragment())
            ft?.commit()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val email = preferences.recuperarEmail().toString()
        binding.tietEmail.setText(email)

    }
}