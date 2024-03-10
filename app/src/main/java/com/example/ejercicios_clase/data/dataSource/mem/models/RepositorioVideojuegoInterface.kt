package com.example.ejercicios_clase.data.dataSource.mem.models

interface RepositorioVideojuegoInterface {
    fun getVideojuegos(): List<Videojuego>
    fun getVideojuegosNota(nota: Int): List<Videojuego>
    fun agregarVideojuego(videojuegoNuevo: Videojuego)
    fun editarVideojuego(pos: Int, videojuegoEditado: Videojuego)
    fun eliminarVideojuego(pos: Int)
}