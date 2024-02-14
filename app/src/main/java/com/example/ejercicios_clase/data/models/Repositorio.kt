package com.example.ejercicios_clase.data.models

import com.example.ejercicios_clase.data.dataSource.Videojuegos

class Repositorio {
    companion object{
        var videojuegos: List<Videojuego> = Videojuegos.videojuegos
    }
}