package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.App.Companion.peliculas
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.R
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityDetalleBinding
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula
import com.squareup.picasso.Picasso
import android.view.*
import android.widget.LinearLayout


class DetalleActivity : AppCompatActivity() {

    companion object {
        lateinit var infoPelicula: Pelicula
    }

    private lateinit var binding: ActivityDetalleBinding

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabPlay.setOnClickListener {
            val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val vista = inflater.inflate(
                R.layout.layout_video,
                null
            )

            val ancho = LinearLayout.LayoutParams.WRAP_CONTENT
            val alto = LinearLayout.LayoutParams.WRAP_CONTENT
            val focusable = true
            val popupWindow = PopupWindow(vista, ancho, alto, focusable)

            popupWindow.showAtLocation(vista, Gravity.CENTER, 0, 0)

            vista.setOnTouchListener(object : View.OnTouchListener {
                @SuppressLint("ClickableViewAccessibility")
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    popupWindow.dismiss()
                    return true
                }
            })

            //val ventana = PopupWindow(this)

            //ventana.contentView = vista
            //val video = vista.findViewById<VideoView>(R.id.vvTrailer)
            //val ruta = infoPelicula.trailer
            //val uri: Uri = Uri.parse(ruta)
            //video.setVideoURI(uri)
            //video.start()
            //ventana.showAtLocation(vista, Gravity.CENTER, 0, 0)
            //video.setOnClickListener {
                //ventana.dismiss()
            }
        }

    override fun onResume() {
        super.onResume()
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
        when (item.itemId) {
            R.id.accion_editar -> {
                val intent = Intent(this, EditarActivity::class.java)
                intent.putExtra("pelicula", infoPelicula)
                startActivity(intent)
                return true
            }
            R.id.accion_borrar -> {
                val builder = AlertDialog.Builder(this)
                val dialog = builder.setTitle(R.string.mensaje_borrar_pelicula)
                    .setMessage(R.string.mensaje_confirmacion_eliminar)
                    .setPositiveButton(R.string.boton_aceptar) { _, _ ->
                        peliculas.remove(infoPelicula)
                        Toast.makeText(this, R.string.toast_pelicula_eliminada, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                    .setNegativeButton(R.string.boton_cancelar, null)
                    .create()
                dialog.show()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    //override fun onStop() {
        //super.onStop()
        //onBackPressed()
    //}

}
