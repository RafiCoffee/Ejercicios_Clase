package com.example.ejercicios_clase.data

import com.example.ejercicios_clase.data.dataSource.Videojuegos

object Estadisticas {
    var totalJuegos: Int = Videojuegos.videojuegos.size
    var totalJuegosAgregados: Int = 0
    var totalJuegosEliminados: Int = 0
    var totalJuegosEditados: Int = 0
}