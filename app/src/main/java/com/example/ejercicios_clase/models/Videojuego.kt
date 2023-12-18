package com.example.ejercicios_clase.models

import android.media.Image
import java.time.LocalDate

class Videojuego (
    var titulo: String,
    var genero: String,
    var nota: Int,
    var fechaSalida: LocalDate,
    var image: Int
) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', fechaSalida='$fechaSalida', image='$image')"
    }
}