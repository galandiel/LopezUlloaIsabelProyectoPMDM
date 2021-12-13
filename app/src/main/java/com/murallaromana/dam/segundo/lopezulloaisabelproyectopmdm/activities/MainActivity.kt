package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityMainBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.fragments.InicioFragment


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val sharedPref = getSharedPreferences("datos", MODE_PRIVATE)
//        val email = sharedPref.getString("email", "")
//        val contrasena = sharedPref.getString("contrasena", "")
//        binding.tietEmail.setText(email?.trim())

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

//        title = "FilmGoer"
//
//        binding.btAcceder.setOnClickListener {
//
//            if (binding.tietEmail.text.toString() == "") {
//                Toast.makeText(this, R.string.toast_introduce_email, Toast.LENGTH_SHORT).show()
//            } else if (!email.equals(binding.tietEmail.text.toString())) {
//                binding.tietEmail.error = getString(R.string.mensaje_usuario_no_existe)
//            } else if (binding.tietContrasena.text.toString() == "") {
//                Toast.makeText(this, R.string.toast_introduce_contrasena, Toast.LENGTH_SHORT).show()
//            } else if (TextUtils.equals(
//                    binding.tietContrasena.text.toString().trim(), contrasena
//                )
//            ) {
//                val intent = Intent(this, PeliculasActivity::class.java)
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, R.string.toast_contrasena_incorrecta, Toast.LENGTH_SHORT)
//                    .show()
//            }
//        }
//
//        binding.tvCrearCuenta.setOnClickListener {
//            val intent = Intent(this, RegistroActivity::class.java)
//            startActivity(intent)
//        }

        supportFragmentManager.addOnBackStackChangedListener {
            //setDisplayHomeAsUp muestra el botón atrás y lo hace "pulsable" (false no se mostraría)
            supportActionBar?.setDisplayHomeAsUpEnabled(supportFragmentManager.backStackEntryCount > 0)
        }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.contenedorFragments, InicioFragment())
        fragmentTransaction.commit()

    }

    override fun onResume() {
        super.onResume()
        if (intent.getBooleanExtra("EXIT", false)) {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

}

