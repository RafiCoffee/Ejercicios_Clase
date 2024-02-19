package com.example.ejercicios_clase.application

import android.content.Context
import android.widget.Toast
import javax.inject.Inject

class PruebaHilt @Inject constructor() {

    fun prueba(contexto: Context){
        Toast.makeText(contexto, "Hilt Funciona", Toast.LENGTH_LONG).show()
    }
}