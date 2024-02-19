package com.example.ejercicios_clase.application

import android.widget.Toast
import javax.inject.Inject

class PruebaHilt @Inject constructor() {
    fun pruebaHilt() : String{
        val pruebaMensaje = "Esto es una prueba de hilt"
        return pruebaMensaje
    }
}