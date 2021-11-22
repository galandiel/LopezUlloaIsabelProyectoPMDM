package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities.DetalleActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ItemPeliculaBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso

class ListaPeliculasAdapter(val peliculas: List<Pelicula>, val context: Context) : RecyclerView.Adapter<ListaPeliculasAdapter.PeliculasViewHolder>() {

    class PeliculasViewHolder(private val itemBinding: ItemPeliculaBinding) :RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(pelicula: Pelicula) {
            itemBinding.tvTitle.text = pelicula.title
            itemBinding.tvMark.text = pelicula.mark.toString()

            Picasso.get().load(pelicula.image).into(itemBinding.ivImage)

            itemBinding.root.setOnClickListener {
                val intent = Intent(itemBinding.root.context, DetalleActivity::class.java)
                intent.putExtra("pelicula", pelicula)
                itemBinding.root.context.startActivity(intent)

            }

            //val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
            //val tvMark = itemView.findViewById<TextView>(R.id.tvMark)
            //val ivImage = itemView.findViewById<ImageView>(R.id.ivImage)
            //val layoutPelicula = itemView.findViewById<CardView>(R.id.layoutPelicula)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeliculasViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
                return PeliculasViewHolder(ItemPeliculaBinding.inflate(layoutInflater, parent, false))

    }

    override fun onBindViewHolder(holder: PeliculasViewHolder, position: Int) {
        holder.bind(peliculas[position])

        //val pelicula = peliculas.get(position)
        //holder.tvTitle.setText(pelicula.title)
        //holder.tvMark.setText((pelicula.mark).toString())
        //Picasso.get().load(pelicula.image).into(holder.ivImage)
        //holder.layoutPelicula.setOnClickListener {
            //val intent = Intent (context, DetalleActivity::class.java)
            // intent.putExtra("título", pelicula.title)
            //intent.putExtra("pelicula", pelicula)
            //context.startActivity(intent)
        }

    override fun getItemCount() = peliculas.size

}