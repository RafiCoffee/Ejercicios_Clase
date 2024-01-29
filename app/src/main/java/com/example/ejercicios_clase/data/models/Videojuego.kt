package com.example.ejercicios_clase.data.models

class Videojuego (var titulo: String, var genero: String, var nota: Int, var fechaSalida: String, var image: Int) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', fechaSalida='$fechaSalida', image='$image')"
    }
}