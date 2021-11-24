package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityAnadirBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class AnadirActivity : AppCompatActivity() {

    lateinit var binding: ActivityAnadirBinding

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
        if (item.itemId == R.id.accion_guardar) {
            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Guardar película")
                .setMessage("Estás a punto de guardar la película, ¿estás seguro?")
                .setPositiveButton("Aceptar", { dialog, id ->
                    val id = peliculas.size
                    val titulo = binding.tietAnadirTitulo.text.toString()
                    val anno = binding.tietAnadirAnno.text.toString()
                    val duracion = binding.tietAnadirDuracion.text.toString()
                    val pais = binding.tietAnadirPais.text.toString()
                    val director = binding.tietAnadirDirector.text.toString()
                    val guion = binding.tietAnadirGuion.text.toString()
                    val musica = binding.tietAnadirMusica.text.toString()
                    val fotografia = binding.tietAnadirFotografia.text.toString()
                    val reparto = binding.tietAnadirReparto.text.toString()
                    val genero = binding.tietAnadirGenero.text.toString()
                    val sinopsis = binding.tietAnadirSinopsis.text.toString()
                    val nota = binding.tietAnadirNota.text.toString()
                    val imagen = binding.tietAnadirImagen.text.toString()
                    peliculas.add(
                        Pelicula(
                            id,
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
                            imagen
                        )
                    )
                    Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show()
                    finish()
                })
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
            return true
        } else if (item.itemId == R.id.accion_cancelar) {
            val intent = Intent(this, PeliculasActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Acción cancelada", Toast.LENGTH_SHORT).show()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}