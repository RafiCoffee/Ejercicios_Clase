package com.example.ejercicios_clase.data.dataSource.mem.service

import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

interface VideojuegoServiceInterface {
    fun getVideojuegos(): List<Videojuego>
    fun getVideojuegosNota(nota: Int): List<Videojuego>
}