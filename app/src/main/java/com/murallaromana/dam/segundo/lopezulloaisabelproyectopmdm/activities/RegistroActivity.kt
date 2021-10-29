package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityRegistroBinding

private lateinit var binding : ActivityRegistroBinding

class RegistroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }
}