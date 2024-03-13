package com.example.ejercicios_clase.data.dataSource.mem.models

class Videojuego (var id: Int, var titulo: String, var genero: String, var nota: Int?, var fechaSalida: String?, var image: Int?) {
    override fun toString(): String {
        return "Videojuego(titulo='$titulo', genero='$genero', nota='$nota', fechaSalida='$fechaSalida', image='$image')"
    }
}