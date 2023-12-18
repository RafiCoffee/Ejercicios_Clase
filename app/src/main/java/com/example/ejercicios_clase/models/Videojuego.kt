package com.example.ejercicios_clase.models

class Videojuego (var titulo: String, var genero: String, var nota: Int, var fechaSalida: String, var image: Int) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', fechaSalida='$fechaSalida', image='$image')"
    }
}