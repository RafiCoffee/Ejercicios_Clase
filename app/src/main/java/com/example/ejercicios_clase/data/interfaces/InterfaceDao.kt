package com.example.ejercicios_clase.data.interfaces

import com.example.ejercicios_clase.data.models.Videojuego
import com.example.ejercicios_clase.data.object_models.Repositorio

interface InterfaceDao {
    fun getDataVideojuegos(): List<Videojuego> = Repositorio.listVideojuegos
}