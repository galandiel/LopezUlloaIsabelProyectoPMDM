package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {

    companion object {
        lateinit var infoPelicula: Pelicula
    }

    private lateinit var binding : ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        infoPelicula = intent.extras?.get("pelicula") as Pelicula

        setTitle(infoPelicula.title)
        binding.tvDetalleAnno.text = infoPelicula.year.toString()
        binding.tvDetalleDuracion.text = infoPelicula.duration
        binding.tvDetallePais.text = infoPelicula.country
        binding.tvDetalleDirector.text = infoPelicula.director
        binding.tvDetalleGuion.text = infoPelicula.script
        binding.tvDetalleMusica.text = infoPelicula.music
        binding.tvDetalleFotografia.text = infoPelicula.photography
        binding.tvDetalleReparto.text = infoPelicula.cast
        binding.tvDetalleGenero.text = infoPelicula.genre
        binding.tvDetalleSinopsis.text = infoPelicula.synopsis
        binding.tvDetalleNota.text = infoPelicula.mark.toString()

        Picasso.get().load(infoPelicula.image).into(binding.ivDetalleImagen)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalle_pelicula, menu)

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_editar) {
            val intent = Intent (this, AnadirActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Editar película", Toast.LENGTH_SHORT).show()
            return true
        } else if (item.itemId == R.id.accion_borrar) {
            Toast.makeText(this, "Película eliminada", Toast.LENGTH_SHORT).show()

            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Borrar película")
                .setMessage("Estás a punto de eliminar la película, ¿estás seguro?")
                .setPositiveButton("Aceptar", {dialog, id ->
                    finish()
                })
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}