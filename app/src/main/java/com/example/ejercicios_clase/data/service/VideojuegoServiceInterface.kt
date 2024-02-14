package com.example.ejercicios_clase.data.service

import com.example.ejercicios_clase.data.models.Videojuego

interface VideojuegoServiceInterface {
    fun getVideojuegos(): List<Videojuego>
    fun getVideojuegosNota(nota: Int): List<Videojuego>
}