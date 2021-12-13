package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.fragments

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.PeliculasActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.RegistroActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityMainBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.FragmentInicioBinding

class InicioFragment : Fragment() {

    private lateinit var binding: FragmentInicioBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = FragmentInicioBinding.inflate(inflater, container, false)

        val sharedPref = activity?.getSharedPreferences("datos", AppCompatActivity.MODE_PRIVATE)
        val email = sharedPref?.getString("email", "")
        val contrasena = sharedPref?.getString("contrasena", "")
        binding.tietEmail.setText(email?.trim())

        activity?.title = "FilmGoer"

        binding.btAcceder.setOnClickListener {

            if (binding.tietEmail.text.toString() == "") {
                Toast.makeText(activity, R.string.toast_introduce_email, Toast.LENGTH_SHORT).show()
            } else if (!email.equals(binding.tietEmail.text.toString())) {
                binding.tietEmail.error = getString(R.string.mensaje_usuario_no_existe)
            } else if (binding.tietContrasena.text.toString() == "") {
                Toast.makeText(activity, R.string.toast_introduce_contrasena, Toast.LENGTH_SHORT).show()
            } else if (TextUtils.equals(
                    binding.tietContrasena.text.toString().trim(), contrasena
                )
            ) {
                val intent = Intent(activity, PeliculasActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(activity, R.string.toast_contrasena_incorrecta, Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.tvCrearCuenta.setOnClickListener {
            val ft = activity?.supportFragmentManager?.beginTransaction()
            ft?.replace(R.id.contenedorFragments, RegistroFragment())
            ft?.commit()
        }

        return binding.root
    }
}