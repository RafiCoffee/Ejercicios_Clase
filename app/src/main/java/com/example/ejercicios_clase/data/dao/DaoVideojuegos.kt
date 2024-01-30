package com.example.ejercicios_clase.data.dao

import com.example.ejercicios_clase.data.interfaces.InterfaceDao
import com.example.ejercicios_clase.data.models.Videojuego
import com.example.ejercicios_clase.data.object_models.Repositorio

class DaoVideojuegos private constructor() : InterfaceDao {
    companion object {
        val mydao: DaoVideojuegos by lazy {
            DaoVideojuegos()
        }
    }

    override fun getDataVideojuegos(): List<Videojuego> = Repositorio.listVideojuegos
}