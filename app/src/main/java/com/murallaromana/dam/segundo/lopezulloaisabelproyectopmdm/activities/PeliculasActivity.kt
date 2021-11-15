package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters.ListaPeliculasAdapter
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityPeliculasBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.PeliculasDaoMockImpl

class PeliculasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPeliculasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Inflo las vistas
        binding = ActivityPeliculasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Obtengo los datos de las peliculas
        val peliculasDao = PeliculasDaoMockImpl()
        val listaPeliculas = peliculasDao.getAll()

        //Creo los componentes que necesita el RecyclerView
        val layoutManager = LinearLayoutManager(this)
        val adapter = ListaPeliculasAdapter(listaPeliculas, this)

        //Asocio el RecyclerView con sus componentes
        binding.rvListaPeliculas.adapter = adapter
        binding.rvListaPeliculas.layoutManager = layoutManager

        binding.fabAnadirPelicula.setOnClickListener {
            val intent = Intent (this, AnadirActivity::class.java)
            startActivity(intent)
        }

    }
}