package com.example.ejercicios_clase.object_models

import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.models.Videojuego
import java.time.LocalDate

object Repositorio {
    val listVideojuegos : List<Videojuego> = listOf(
        Videojuego("Zelda: Breath Of The Wild","Aventura",5, LocalDate.of(2017, 3, 3), R.drawable.zelda_botw_portada),
        Videojuego("Elden Ring" ,"Souls", 4, LocalDate.of(2022, 2, 25), R.drawable.elden_ring_portada),
        Videojuego("No Man's Sky" ,"Exploración", 2, LocalDate.of(2016, 9, 9), R.drawable.no_mans_sky_portada))
}