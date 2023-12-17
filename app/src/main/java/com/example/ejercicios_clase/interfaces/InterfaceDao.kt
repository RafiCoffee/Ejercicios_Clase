package com.example.ejercicios_clase.interfaces

import com.example.ejercicios_clase.models.Videojuego
import com.example.ejercicios_clase.object_models.Repositorio

interface InterfaceDao {
    fun getDataVideojuegos(): List<Videojuego> = Repositorio.listVideojuegos
}