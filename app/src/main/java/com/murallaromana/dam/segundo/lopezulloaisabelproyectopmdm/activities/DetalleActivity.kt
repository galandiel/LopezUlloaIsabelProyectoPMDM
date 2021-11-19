package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class DetalleActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetalleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val infoPelicula = intent.extras?.get("pelicula") as Pelicula

        setTitle(infoPelicula.title)
        binding.tvDetalleTitulo.text = infoPelicula.title
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
}