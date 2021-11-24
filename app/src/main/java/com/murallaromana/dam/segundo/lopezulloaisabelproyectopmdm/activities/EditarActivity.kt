package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityAnadirBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityEditarBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

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

        setTitle("Editar película")
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
                    peliculas.remove(infoPelicula)
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
            Toast.makeText(this, "Acción cancelada", Toast.LENGTH_SHORT).show()
            finish()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}