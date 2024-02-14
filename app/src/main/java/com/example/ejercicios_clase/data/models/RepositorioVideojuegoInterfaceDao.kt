package com.example.ejercicios_clase.data.models

interface RepositorioVideojuegoInterfaceDao {
    fun getVideojuegos(): List<Videojuego>
    fun getVideojuegosNota(nota: Int): List<Videojuego>
    fun agregarVideojuego(videojuegoNuevo: Videojuego)
    fun editarVideojuego(pos: Int, videojuegoEditado: Videojuego)
    fun eliminarVideojuego(pos: Int)
}