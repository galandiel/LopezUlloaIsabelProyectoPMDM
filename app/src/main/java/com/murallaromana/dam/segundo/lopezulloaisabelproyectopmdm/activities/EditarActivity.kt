package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityEditarBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula

class EditarActivity : AppCompatActivity() {

    companion object {
        lateinit var infoPelicula: Pelicula
    }

    lateinit var binding: ActivityEditarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoPelicula = intent.extras?.get("pelicula") as Pelicula

        binding.tietAnadirTitulo.setText(infoPelicula.titulo)
        binding.tietAnadirAnno.setText(infoPelicula.anno)
        binding.tietAnadirDuracion.setText(infoPelicula.duracion)
        binding.tietAnadirPais.setText(infoPelicula.pais)
        binding.tietAnadirDirector.setText(infoPelicula.director)
        binding.tietAnadirGuion.setText(infoPelicula.guion)
        binding.tietAnadirMusica.setText(infoPelicula.musica)
        binding.tietAnadirFotografia.setText(infoPelicula.fotografia)
        binding.tietAnadirReparto.setText(infoPelicula.reparto)
        binding.tietAnadirGenero.setText(infoPelicula.genero)
        binding.tietAnadirSinopsis.setText(infoPelicula.sinopsis)
        binding.tietAnadirNota.setText(infoPelicula.nota)
        binding.tietAnadirImagen.setText(infoPelicula.imagen)
        binding.tietAnadirTrailer.setText(infoPelicula.trailer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editar_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_guardar) {

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
                    .setPositiveButton(R.string.boton_aceptar, { dialog, id ->
                        val indicePelicula = peliculas.indexOf(infoPelicula)
                        val peliculaEditada = Pelicula(
                            infoPelicula.id,
                            titulo,
                            anno,
                            duracion,
                            pais,
                            director,
                            guion,
                            musica,
                            fotografia,
                            reparto,
                            genero,
                            sinopsis,
                            nota,
                            imagen,
                            trailer
                        )
                        peliculas[indicePelicula] = peliculaEditada

                        Toast.makeText(this, R.string.toast_pelicula_guardada, Toast.LENGTH_SHORT).show()
                        finish()
                    })
                    .setNegativeButton(R.string.boton_cancelar, null)
                    .create()
                dialog.show()
            }
            return true
        } else if (item.itemId == R.id.accion_cancelar) {
            Toast.makeText(this, R.string.toast_accion_cancelada, Toast.LENGTH_SHORT).show()
            finish()
            return true
        } else if (item.itemId == R.id.accion_ayuda) {
            val numeroTelefono = "0034617974641"
            if (!TextUtils.isEmpty(numeroTelefono)) {
                val dial = "tel:$numeroTelefono"
                startActivity(Intent(Intent.ACTION_DIAL, Uri.parse(dial)))
            } else {
                Toast.makeText(this, R.string.toast_error, Toast.LENGTH_SHORT).show()
            }
            return false
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}