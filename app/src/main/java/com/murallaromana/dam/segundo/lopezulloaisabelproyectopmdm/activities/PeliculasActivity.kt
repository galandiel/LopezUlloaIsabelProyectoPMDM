package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.config.retrofitConfig.RetrofitClient.apiRetrofit
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.config.bdConfig.AppDatabase
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityPeliculasBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.utils.ValidacionesUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class PeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculasBinding
    private lateinit var peliculas: List<Pelicula>

    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflar las vistas
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

        val token = "Bearer " + preferences.recuperarToken()

        //Si no hay internet, obtener las películas de Room y esconder el botón de añadir
        if (!ValidacionesUtils().hayConexion(context)) {
            val db = AppDatabase.getDatabase(context)
            val peliculaDao = db?.peliculaDao()
            GlobalScope.launch(Dispatchers.IO) {
                peliculas = peliculaDao!!.findAll()

                runOnUiThread {
                    binding.fabAnadirPelicula.hide()
                    Toast.makeText(context, R.string.toast_no_internet, Toast.LENGTH_LONG).show()

                    //Creo los componentes que necesita el RecyclerView
                    val layoutManager = LinearLayoutManager(context)
                    val adapter = ListaPeliculasAdapter(peliculas, context)

                    //Asocio el RecyclerView con sus componentes
                    binding.rvListaPeliculas.adapter = adapter
                    binding.rvListaPeliculas.layoutManager = layoutManager
                }
            }

        } else {
            //Obtener todas las películas
            val llamadaApi: Call<List<Pelicula>> = apiRetrofit.getPeliculas(token)
            llamadaApi.enqueue(object : Callback<List<Pelicula>> {
                override fun onResponse(
                    call: Call<List<Pelicula>>,
                    response: Response<List<Pelicula>>
                ) {
                    //Obtener los datos de las peliculas
                    val peliculas = response.body()

                    //Guardar la fecha actual en sharedPreferences
                    val c = Calendar.getInstance()
                    val sdf = SimpleDateFormat("dd-MM-yyyy")
                    val fecha = sdf.format(c.time)

                    /*Si la fecha actual no coincide con la quardada en sharedPreferences, guardar
                    tanto la fecha como las películas*/
                    if (preferences.recuperarFecha() != fecha || preferences.recuperarFecha() == null) {
                        preferences.guardarFecha(fecha)
                        val db = AppDatabase.getDatabase(context)
                        val peliculaDao = db?.peliculaDao()
                        GlobalScope.launch(Dispatchers.IO) {
                            peliculaDao?.deleteAll()
                            response.body()?.forEach { it -> it.idroom = it.id!! }
                            peliculaDao?.insertAll(response.body())
                        }
                    }

                    if (response.code() < 200 || response.code() > 299 || peliculas == null) {
                        Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show()
                        if (response.code() == 401) {
                            ValidacionesUtils().reiniciarApp(context)
                        }
                    } else {
                        //Mostrar el botón de añadir
                        binding.fabAnadirPelicula.show()
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
                }
            })
        }
    }

}