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

class EditarActivity : AppCompatActivity() {

    companion object {
        lateinit var infoPelicula: Pelicula
    }

    lateinit var binding: ActivityEditarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editar_pelicula, menu)

        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.accion_guardar) {
            Toast.makeText(this, "Película guardada", Toast.LENGTH_SHORT).show()
            val builder = AlertDialog.Builder(this)
            val dialog = builder.setTitle("Guardar película")
                .setMessage("Estás a punto de guardar la película, ¿estás seguro?")
                .setPositiveButton("Aceptar", { dialog, id ->
                    peliculas.remove(infoPelicula)

                    finish()
                })
                .setNegativeButton("Cancelar", null)
                .create()

            dialog.show()

            return true
        } else if (item.itemId == R.id.accion_cancelar) {
            Toast.makeText(this, "Acción cancelada", Toast.LENGTH_SHORT).show()

            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
    }

}