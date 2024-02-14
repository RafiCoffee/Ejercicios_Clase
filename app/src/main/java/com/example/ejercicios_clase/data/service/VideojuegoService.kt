package com.example.ejercicios_clase.data.service

import android.util.Log
import com.example.ejercicios_clase.data.dataSource.Videojuegos
import com.example.ejercicios_clase.data.models.Repositorio
import com.example.ejercicios_clase.data.models.Videojuego

class VideojuegoService : VideojuegoServiceInterface {
    override fun getVideojuegos(): List<Videojuego> {
        return Repositorio.videojuegos
    }

    override fun getVideojuegosNota(nota: Int): List<Videojuego> {
        val videojuegosNota = Repositorio.videojuegos.filter {
            it.nota.equals(nota)
        }
        return videojuegosNota
    }
}