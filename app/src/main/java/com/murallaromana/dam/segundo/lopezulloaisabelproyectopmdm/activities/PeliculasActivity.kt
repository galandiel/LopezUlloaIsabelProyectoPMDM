package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient.apiRetrofit
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityPeliculasBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.utils.ValidacionesUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculasBinding
    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflo las vistas
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Botón para añadir películas
        binding.fabAnadirPelicula.setOnClickListener {
            val intent = Intent(this, AnadirActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_peliculas, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            /*R.id.accion_salir -> {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle(R.string.mensaje_salir)
                    .setMessage(R.string.mensaje_confirmacion_salir)
                    .setPositiveButton(R.string.boton_aceptar) { _, _ ->
                        val intent = Intent(this, MainActivity::class.java)
                        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                        startActivity(intent)
                    }
                    .setNegativeButton(R.string.boton_cancelar, null)
                    .create()
                dialog.show()
                return true
            }*/
            R.id.accion_cerrar_sesion -> {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle(R.string.mensaje_cerrar_sesion)
                    .setMessage(R.string.mensaje_confirmacion_cerrar_sesion)
                    .setPositiveButton(R.string.boton_aceptar) { _, _ ->
                        ValidacionesUtils().reiniciarApp(this)
                        preferences.guardarToken("")

                    }
                    .setNegativeButton(R.string.boton_cancelar, null)
                    .create()
                dialog.show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        preferences = Preferences(this)
        val context = this

        var token = "Bearer " + preferences.recuperarToken("")

        val llamadaApi: Call<List<Pelicula>> = apiRetrofit.getPeliculas(token)
        llamadaApi.enqueue(object: Callback<List<Pelicula>> {
            override fun onResponse(call: Call<List<Pelicula>>, response: Response<List<Pelicula>>) {

                //Obtengo los datos de las peliculas
                val peliculas = response.body()

                if (response.code() < 200 || response.code() > 299 || peliculas == null){
                    Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show()
                    if (response.code() == 401 || response.code() == 500) {
                        ValidacionesUtils().reiniciarApp(context)
                    }
                } else {
                    //Creo los componentes que necesita el RecyclerView
                    val layoutManager = LinearLayoutManager(context)
                    val adapter = ListaPeliculasAdapter(peliculas, context)

                    //Asocio el RecyclerView con sus componentes
                    binding.rvListaPeliculas.adapter = adapter
                    binding.rvListaPeliculas.layoutManager = layoutManager
                }

            }
            override fun onFailure(call: Call<List<Pelicula>>, t: Throwable) {
                Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show()
                Log.d("prueba", t.message.toString())
            }
        })
    }

}