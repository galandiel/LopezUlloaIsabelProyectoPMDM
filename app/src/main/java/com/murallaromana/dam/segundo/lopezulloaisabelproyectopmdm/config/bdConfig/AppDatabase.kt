package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.config.bdConfig

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.dao.room.PeliculaDao
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.model.entities.Pelicula


@Database(entities = [Pelicula::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun peliculaDao(): PeliculaDao
    companion object {
        private var database: AppDatabase? = null
        // Singleton
        fun getDatabase(context: Context): AppDatabase? {
        // ?: dice que si la parte izquierda es nula ejecute la de la derecha
            database ?: synchronized(this) {
                database = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "BaseDatosPeliculas"
                ).build()
            }
            return database
        }
    }
}

