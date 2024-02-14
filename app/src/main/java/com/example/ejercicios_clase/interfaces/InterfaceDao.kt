package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.data.models.Videojuego
import com.example.ejercicios_clase.data.dataSource.Videojuegos

interface InterfaceDao {
    fun getDataVideojuegos(): List<Videojuego> = Videojuegos.videojuegos
}