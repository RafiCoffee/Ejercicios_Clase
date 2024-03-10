package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

interface InterfaceDao {
    fun getDataVideojuegos(): List<Videojuego> = Videojuegos.videojuegos
}