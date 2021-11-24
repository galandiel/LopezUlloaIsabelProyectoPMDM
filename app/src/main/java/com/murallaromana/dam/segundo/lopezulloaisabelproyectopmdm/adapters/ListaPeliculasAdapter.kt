package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.DetalleActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ItemPeliculaBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class ListaPeliculasAdapter(val peliculas: List<Pelicula>, val context: Context) :
    RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasViewHolder>() {

    class PeliculasViewHolder(private val itemBinding: ItemPeliculaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pelicula: Pelicula) {
            itemBinding.tvTitle.text = pelicula.titulo
            val nota = pelicula.nota.toFloat()/2
            itemBinding.ratingBar.rating = nota

            Picasso.get().load(pelicula.imagen).into(itemBinding.ivImage)

            itemBinding.root.setOnClickListener {
                val intent = Intent(itemBinding.root.context, DetalleActivity::class.java)
                intent.putExtra("pelicula", pelicula)
                itemBinding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PeliculasViewHolder(ItemPeliculaBinding.inflate(layoutInflater, parent, false))
    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.bind(peliculas[position])
    }

    override fun getItemCount() = peliculas.size

}