package com.example.ejercicios_clase.data.retrofit.responses

import com.example.ejercicios_clase.data.dataSource.mem.models.Videojuego

data class ResponseVideojuegos (val result: String, val videojuegos: List<Videojuego>)