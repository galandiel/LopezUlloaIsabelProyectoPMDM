package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {

    companion object {
        lateinit var infoPelicula: Pelicula
    }

    private lateinit var binding: ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoPelicula = intent.extras?.get("pelicula") as Pelicula

        title = infoPelicula.titulo
        binding.tvDetalleAnno.text = infoPelicula.anno
        binding.tvDetalleDuracion.text = infoPelicula.duracion
        binding.tvDetallePais.text = infoPelicula.pais
        binding.tvDetalleDirector.text = infoPelicula.director
        binding.tvDetalleGuion.text = infoPelicula.guion
        binding.tvDetalleMusica.text = infoPelicula.musica
        binding.tvDetalleFotografia.text = infoPelicula.fotografia
        binding.tvDetalleReparto.text = infoPelicula.reparto
        binding.tvDetalleGenero.text = infoPelicula.genero
        binding.tvDetalleSinopsis.text = infoPelicula.sinopsis

        Picasso.get().load(infoPelicula.imagen).into(binding.ivDetalleImagen)

        val nota = infoPelicula.nota.toFloat()/2
        binding.ratingBarDetalle.rating = nota
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_editar) {
            val intent = Intent(this, EditarActivity::class.java)
            intent.putExtra("pelicula", infoPelicula)
            startActivity(intent)
            return true
        } else if (item.itemId == R.id.accion_borrar) {
            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Borrar película")
                .setMessage("Estás a punto de eliminar la película, ¿estás seguro?")
                .setPositiveButton("Aceptar") { dialog, id ->
                    peliculas.remove(infoPelicula)
                    Toast.makeText(this, "Película eliminada", Toast.LENGTH_SHORT).show()
                    finish()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}