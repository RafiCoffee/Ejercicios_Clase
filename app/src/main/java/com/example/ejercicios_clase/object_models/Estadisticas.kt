package com.example.ejercicios_clase.object_models

object Estadisticas {
    var totalJuegos: Int = Repositorio.listVideojuegos.size
    var totalJuegosAgregados: Int = 0
    var totalJuegosEliminados: Int = 0
    var totalJuegosEditados: Int = 0
}