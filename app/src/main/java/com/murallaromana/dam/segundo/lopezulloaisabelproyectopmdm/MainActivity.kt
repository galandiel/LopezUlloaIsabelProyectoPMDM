package com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.murallaromana.dam.segundo.lopezulloaisabelproyectopmdm.databinding.ActivityMainBinding

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}