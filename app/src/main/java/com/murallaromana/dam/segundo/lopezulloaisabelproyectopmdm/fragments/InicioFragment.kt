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
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient.apiRetrofit
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentInicioBinding.inflate(inflater, container, false)
        con = requireContext().applicationContext

        /*val sharedPref = activity?.getSharedPreferences("datos", AppCompatActivity.MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        val contrasena = sharedPref?.getString("contrasena", "")
        binding.tietEmail.setText(email?.trim())*/

        activity?.title = "FilmGoer"

        preferences = Preferences(con)



        binding.btAcceder.setOnClickListener {

            var email = binding.tietEmail.text.toString().trim()
            var contrasenha = binding.tietContrasena.text.toString().trim()

            if (email == "") {
                Toast.makeText(activity, R.string.toast_introduce_email, Toast.LENGTH_SHORT).show()
            } else if (contrasenha == "") {
                Toast.makeText(activity, R.string.toast_introduce_contrasena, Toast.LENGTH_SHORT).show()
            } else {

                val u = Usuario(null, email, contrasenha)

                val loginCall = apiRetrofit.iniciar(u)

                loginCall.enqueue(object: Callback<Token> {
                    override fun onFailure(call: Call<Token>, t: Throwable) {
                        Toast.makeText(activity, R.string.toast_no_iniciado, Toast.LENGTH_SHORT).show()
                        Log.d("respuesta: onFailure", t.toString())

                    }

                    override fun onResponse(call: Call<Token>, response: Response<Token>) {
                        Log.d("respuesta: onResponse", response.toString())

                        if (response.code() < 200 || response.code() > 299) {
                            Toast.makeText(activity, R.string.toast_no_iniciado, Toast.LENGTH_SHORT).show()

                        } else {

                            //Guardo en sharedPreferences el token
                            val token = response.body()?.token.toString()
                            preferences.guardarToken(token)

                            Log.d("respuesta: token:", token.orEmpty())
                            //Inicio nueva activity
                            val intent = Intent(activity, PeliculasActivity::class.java)
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
        var email = preferences.recuperarEmail("").toString()
        if (email != null) {
            binding.tietEmail.setText(email)
        }
    }
}