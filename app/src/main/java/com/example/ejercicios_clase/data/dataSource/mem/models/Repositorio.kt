package com.example.ejercicios_clase.data.dataSource.mem.models

import com.example.ejercicios_clase.data.dataSource.mem.Videojuegos
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Repositorio {
    companion object{
        var videojuegos: List<Videojuego> = Videojuegos.videojuegos
    }
}