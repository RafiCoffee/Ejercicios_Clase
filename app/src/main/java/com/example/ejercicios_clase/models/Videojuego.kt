package com.example.ejercicios_clase.models

import android.media.Image
import java.util.Date

class Videojuego (
    var titulo: String,
    var genero: String,
    var nota: Int,
    var annoSalida: Int,
    var image: Int
) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', annoSalida='$annoSalida', image='$image')"
    }
}