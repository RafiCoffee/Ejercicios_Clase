package com.example.ejercicios_clase.dao

import com.example.ejercicios_clase.interfaces.InterfaceDao
import com.example.ejercicios_clase.data.models.Videojuego
import com.example.ejercicios_clase.data.dataSource.Videojuegos

class DaoVideojuegos private constructor() : InterfaceDao {
    companion object {
        val mydao: DaoVideojuegos by lazy {
            DaoVideojuegos()
        }
    }

    override fun getDataVideojuegos(): List<Videojuego> = Videojuegos.videojuegos
}