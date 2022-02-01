package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.RetrofitClient.apiRetrofit
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityAnadirBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.Preferences
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AnadirActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnadirBinding
    companion object {
        lateinit var preferences: Preferences
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editar_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.accion_guardar -> {

                val titulo = binding.tietAnadirTitulo.text.toString().trim()
                val anno = binding.tietAnadirAnno.text.toString().trim()
                val duracion = binding.tietAnadirDuracion.text.toString().trim()
                val pais = binding.tietAnadirPais.text.toString().trim()
                val director = binding.tietAnadirDirector.text.toString().trim()
                val guion = binding.tietAnadirGuion.text.toString().trim()
                val musica = binding.tietAnadirMusica.text.toString().trim()
                val fotografia = binding.tietAnadirFotografia.text.toString().trim()
                val reparto = binding.tietAnadirReparto.text.toString().trim()
                val genero = binding.tietAnadirGenero.text.toString().trim()
                val sinopsis = binding.tietAnadirSinopsis.text.toString().trim()
                val nota = binding.tietAnadirNota.text.toString().trim()
                val imagen = binding.tietAnadirImagen.text.toString().trim()
                val trailer = binding.tietAnadirTrailer.text.toString().trim()

                if (TextUtils.isEmpty(titulo) ||
                    TextUtils.isEmpty(anno) ||
                    TextUtils.isEmpty(duracion) ||
                    TextUtils.isEmpty(pais) ||
                    TextUtils.isEmpty(director) ||
                    TextUtils.isEmpty(guion) ||
                    TextUtils.isEmpty(musica) ||
                    TextUtils.isEmpty(fotografia) ||
                    TextUtils.isEmpty(reparto) ||
                    TextUtils.isEmpty(genero) ||
                    TextUtils.isEmpty(sinopsis) ||
                    TextUtils.isEmpty(nota) ||
                    TextUtils.isEmpty(imagen) ||
                    TextUtils.isEmpty(trailer)
                ) {
                    Toast.makeText(this, R.string.toast_campos_vacios, Toast.LENGTH_SHORT).show()
                } else {
                    val builder = AlertDialog.Builder(this)
                    val dialog = builder.setTitle(R.string.mensaje_guardar_pelicula)
                        .setMessage(R.string.mensaje_guardar_pelicula)
                        .setPositiveButton(R.string.boton_aceptar) { _, _ ->
                            var pelicula = Pelicula(null,
                                binding.tietAnadirTitulo.text.toString(),
                                binding.tietAnadirAnno.text.toString(),
                                binding.tietAnadirDuracion.text.toString(),
                                binding.tietAnadirPais.text.toString(),
                                binding.tietAnadirDirector.text.toString(),
                                binding.tietAnadirGuion.text.toString(),
                                binding.tietAnadirMusica.text.toString(),
                                binding.tietAnadirFotografia.text.toString(),
                                //binding.tietAnadirReparto.text.toString(),
                                null,
                                binding.tietAnadirGenero.text.toString(),
                                binding.tietAnadirSinopsis.text.toString(),
                                binding.tietAnadirNota.text.toString(),
                                binding.tietAnadirImagen.text.toString(),
                                binding.tietAnadirTrailer.text.toString())

                            preferences = Preferences(this)
                            val context = this

                            var token = "Bearer " + preferences.recuperarToken("")

                            val llamadaApi: Call<Unit> = apiRetrofit.anadir(token, pelicula)
                            llamadaApi.enqueue(object: Callback<Unit> {
                                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {


                                    if (response.code() < 200 || response.code() > 299){
                                        Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show()

                                    } else {
                                        Toast.makeText(context, R.string.toast_pelicula_guardada, Toast.LENGTH_SHORT).show()
                                        finish()
                                    }


                                    //Toast.makeText(context, response.body().toString(),Toast.LENGTH_SHORT).show()
                                }
                                override fun onFailure(call: Call<Unit>, t: Throwable) {
                                    Toast.makeText(context, R.string.toast_error, Toast.LENGTH_SHORT).show()
                                    Log.d("prueba", t.message.toString())
                                }
                            })

                            /*peliculas.add(
                                Pelicula(
                                    peliculas.size.toString(),
                                    binding.tietAnadirTitulo.text.toString(),
                                    binding.tietAnadirAnno.text.toString(),
                                    binding.tietAnadirDuracion.text.toString(),
                                    binding.tietAnadirPais.text.toString(),
                                    binding.tietAnadirDirector.text.toString(),
                                    binding.tietAnadirGuion.text.toString(),
                                    binding.tietAnadirMusica.text.toString(),
                                    binding.tietAnadirFotografia.text.toString(),
                                    binding.tietAnadirReparto.text.toString(),
                                    binding.tietAnadirGenero.text.toString(),
                                    binding.tietAnadirSinopsis.text.toString(),
                                    binding.tietAnadirNota.text.toString(),
                                    binding.tietAnadirImagen.text.toString(),
                                    binding.tietAnadirTrailer.text.toString()
                                )
                            )*/

                        }
                        .setNegativeButton(R.string.boton_cancelar, null)
                        .create()
                    dialog.show()
                }
                return true
            }
            R.id.accion_cancelar -> {
                finish()
                Toast.makeText(this, R.string.toast_accion_cancelada, Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.accion_ayuda -> {
                val numeroTelefono = "0034617974641"
                if (!TextUtils.isEmpty(numeroTelefono)) {
                    val dial = "tel:$numeroTelefono"
                    startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
                } else {
                    Toast.makeText(this, R.string.toast_error, Toast.LENGTH_SHORT).show()
                }
                return false
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
}


