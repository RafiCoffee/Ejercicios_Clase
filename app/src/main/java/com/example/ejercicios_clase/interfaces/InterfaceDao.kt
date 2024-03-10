package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego
import com.example.ejercicios_clase.data.dataSource.mem.Videojuegos

interface InterfaceDao {
    fun getDataVideojuegos(): List<Videojuego> = Videojuegos.videojuegos
}