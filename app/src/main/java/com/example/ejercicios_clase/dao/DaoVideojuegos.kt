package com.example.ejercicios_clase.dao

import com.example.ejercicios_clase.interfaces.InterfaceDao
import com.example.ejercicios_clase.models.Videojuego
import com.example.ejercicios_clase.object_models.Repositorio

class DaoVideojuegos private constructor() : InterfaceDao {
    companion object {
        val mydao: DaoVideojuegos by lazy {
            DaoVideojuegos()
        }
    }

    override fun getDataVideojuegos(): List<Videojuego> = Repositorio.listVideojuegos
}