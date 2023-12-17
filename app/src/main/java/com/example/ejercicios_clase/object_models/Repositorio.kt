package com.example.ejercicios_clase.object_models

import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.models.Videojuego

object Repositorio {
    val listVideojuegos : List<Videojuego> = listOf(
        Videojuego("Zelda:\nBreath Of The Wild","Aventura",5, 2017, R.drawable.zelda_botw_portada),
        Videojuego("Elden Ring" ,"Souls", 4,2022, R.drawable.elden_ring_portada),
        Videojuego("Red Dead Redemption 2" ,"Aventura", 5,2018, R.drawable.rdr2_portada))
}