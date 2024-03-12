package com.example.ejercicios_clase.data.dataSource.mem

import com.example.ejercicios_clase.R
import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

object Videojuegos {
    var videojuegos : List<Videojuego> = listOf(
        Videojuego("Zelda: Breath Of The Wild","Aventura",5, "2017/03/03", R.drawable.zelda_botw_portada),
        Videojuego("Elden Ring" ,"Souls", 4, "2022/02/25", R.drawable.elden_ring_portada),
        Videojuego("No Man's Sky" ,"Exploración", 2, "2016/09/09", R.drawable.no_mans_sky_portada)
    )
}